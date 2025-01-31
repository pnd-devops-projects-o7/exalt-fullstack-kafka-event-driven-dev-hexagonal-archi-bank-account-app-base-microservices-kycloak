package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Customer;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerApiWebTest {
    @InjectMocks
    private CustomerApiWeb customerApiWeb;
    @Mock
    private InputService inputService;

    private AutoCloseable autoCloseable;

    private final CustomerRequestDto customerRequestDto = new CustomerRequestDto(
            new CustomerDto("test", "test", "test", "test@domain.com", "test",
                    Instant.now()), new AddressDto("test", 1, "test", 59300,
            "test", "France", "France")
    );

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createCustomer() {
        //execute
        Mockito.when(inputService.createCustomer(customerRequestDto))
                .thenReturn(Mockito.mock(CustomerResponseDto.class));
        ResponseEntity<CustomerResponseDto> response = customerApiWeb.createCustomer(customerRequestDto);
        //verify
        Assertions.assertAll("**",()->{
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Mockito.verify(inputService, Mockito.atLeast(1)).createCustomer(customerRequestDto);
        });
    }

    @Test
    void getAllCustomers() {
        //execute
        Mockito.when(inputService.loadAllPersistedCustomers()).thenReturn(List.of(Mockito.mock(CustomerResponseDto.class)));
        ResponseEntity<List<CustomerResponseDto>> response = customerApiWeb.getAllCustomers();
        //execute
        Assertions.assertAll("**",()->{
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Mockito.verify(inputService).loadAllPersistedCustomers();
        });
    }

    @Test
    void getCustomerById() {
        //prepare
        final String customerId = UUID.randomUUID().toString();
        //execute
        Mockito.when(inputService.getCustomerById(customerId)).thenReturn(Mockito.mock(CustomerResponseDto.class));
        CustomerResponseDto response = customerApiWeb.getCustomerById(customerId);
        //verify
        Assertions.assertAll("**",()->{
            Assertions.assertNotNull(response);
            Mockito.verify(inputService, Mockito.atLeast(1)).getCustomerById(customerId);
        });
    }

    @Test
    void getCustomerBy() {
        //execute
        Mockito.when(inputService.getCustomerBy(customerRequestDto.customerDto().firstname(), customerRequestDto.customerDto().lastname(),
                customerRequestDto.customerDto().email())).thenReturn(Mockito.mock(CustomerResponseDto.class));
        ResponseEntity<CustomerResponseDto> response = customerApiWeb.getCustomerBy(
                customerRequestDto.customerDto().firstname(), customerRequestDto.customerDto().lastname(),
                customerRequestDto.customerDto().email());
        //verify
        Assertions.assertAll("**",()->{
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Mockito.verify(inputService, Mockito.atLeast(1)).getCustomerBy(
                    customerRequestDto.customerDto().firstname(), customerRequestDto.customerDto().lastname(),
                    customerRequestDto.customerDto().email());
        });
    }

    @Test
    void archiveCustomer() {
        //execute
        Mockito.when(inputService.archiveCustomer(Mockito.any()))
                .thenReturn(Mockito.mock(CustomerResponseDto.class));
        ResponseEntity<CustomerResponseDto> response = customerApiWeb.archiveCustomer(Mockito.any());
        //verify
        Assertions.assertAll("**",()->{
            Assertions.assertNotNull(response);
            Mockito.verify(inputService, Mockito.atLeast(1))
                    .archiveCustomer(Mockito.any());
        });

    }

    @Test
    void customerSwitchState() {
        //execute
        Mockito.when(inputService.customerSwitchState(Mockito.any()))
                .thenReturn(Mockito.mock(CustomerResponseDto.class));
        ResponseEntity<CustomerResponseDto> response =  customerApiWeb.customerSwitchState(Mockito.any());
        //verify
        Assertions.assertAll("**",()->{
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Mockito.verify(inputService, Mockito.atLeast(1))
                    .customerSwitchState(Mockito.any());
        });
    }

    @Test
    void updateCustomerInfo() {
        //execute
        Mockito.when(inputService.updateCustomerInfo(Mockito.anyString(), Mockito.any()))
                .thenReturn(Mockito.mock(CustomerResponseDto.class));
        ResponseEntity<CustomerResponseDto> response =  customerApiWeb.updateCustomerInfo(Mockito.anyString(), Mockito.any());
        Assertions.assertAll("**",()->{
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Mockito.verify(inputService, Mockito.atLeast(1))
                    .updateCustomerInfo(Mockito.anyString(), Mockito.any());
        });
    }
}