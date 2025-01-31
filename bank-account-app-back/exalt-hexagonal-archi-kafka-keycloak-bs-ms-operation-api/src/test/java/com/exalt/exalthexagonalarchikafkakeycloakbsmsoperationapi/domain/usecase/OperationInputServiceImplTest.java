package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.OperationInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OperationOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteAccountService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteCustomerService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperationInputServiceImplTest {
    @InjectMocks
    private OperationInputServiceImpl opServiceImpl;
    @Mock
    private RemoteAccountService remoteAccountService;
    @Mock
    private RemoteCustomerService remoteCustomerService;
    @Mock
    private OperationOutputService outputService;
    @Mock
    private EventProducer eventProducer;
    private AutoCloseable autoCloseable;

    private final String accountId = "accountId";

    private final OperationRequestDto operationRequestDto = new OperationRequestDto(
            "DEPOSIT","OPERATION DE DEPOT",accountId);
    private final BigDecimal amount = BigDecimal.valueOf(2000.0);
    private final CustomerResponseDto customerResponseDto = new CustomerResponseDto(
           new CustomerDto("customerId","placide","nduwayo","placide.nduwayo@domain.com","ACTIVE",
                   Instant.now()),
            new AddressDto("addressId",17,"Rue Henri Discret",57100,"Thionville","France",
                    "Burundi"));

    private final AccountResponseDto accountResponseDto = new AccountResponseDto(
            accountId,"CURRENT","ACTIVE",amount,200.0,0.0, Instant.now(),
           customerResponseDto);

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createOperation() {
        //execute
        Mockito.when(remoteAccountService.getRemoteAccountById(operationRequestDto.accountId()))
                .thenReturn(accountResponseDto);
        Mockito.when(remoteCustomerService.getRemoteCustomerById(accountResponseDto.customerResponseDto().customerDto().customerId()))
                .thenReturn(customerResponseDto);
        OperationResponseDto operationResponseDto = opServiceImpl.createOperation(operationRequestDto, amount);
        //verify
        Assertions.assertAll("**",()->{
            Assertions.assertNotNull(operationResponseDto);
            Mockito.verify(remoteAccountService).getRemoteAccountById(operationRequestDto.accountId());
            Mockito.verify(remoteCustomerService,Mockito.atLeast(1))
                    .getRemoteCustomerById(accountResponseDto.customerResponseDto().customerDto().customerId());
        });
    }

    @Test
    void getAllOperations() {
        //execute
        Mockito.when(outputService.getAllOperations()).thenReturn(List.of());
        Collection<OperationResponseDto> operations = opServiceImpl.getAllOperations();
        //verify
        Assertions.assertNotNull(operations);
    }

    @Test
    void getOperationById() {
        //prepare
        final String operationId = "operationId";
        //execute
        Mockito.when(outputService.getOperationById(operationId)).thenReturn(Mockito.mock(OperationEntity.class));
        OperationResponseDto operationResponseDto = opServiceImpl.getOperationById(operationId);
        Assertions.assertAll("**",()->{
            Assertions.assertNotNull(operationResponseDto);
            Mockito.verify(outputService,Mockito.atLeastOnce()).getOperationById(operationId);
        });
    }
}