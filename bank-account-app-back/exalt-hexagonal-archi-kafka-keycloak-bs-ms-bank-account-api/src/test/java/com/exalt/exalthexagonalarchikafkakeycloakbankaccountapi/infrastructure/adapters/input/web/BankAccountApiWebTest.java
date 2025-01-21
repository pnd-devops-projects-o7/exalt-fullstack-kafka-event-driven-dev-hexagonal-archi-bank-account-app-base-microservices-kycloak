package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountApiWebTest {
    private AutoCloseable closeable;
    @Mock
    private InputService inputServiceMock;
    @InjectMocks
    private BankAccountApiWeb underTest;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllBankAccounts() {
        //execute
        Mockito.when(inputServiceMock.getAllBankAccounts()).thenReturn(List.of());
        ResponseEntity<Collection<AccountResponseDto>> responseEntity = underTest.getAllBankAccounts();
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(inputServiceMock, Mockito.atLeast(1)).getAllBankAccounts();
            Assertions.assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        });
    }

    @Test
    void createBankAccount() {
        //prepare
        final AccountRequestDto accountRequestDto = new
                AccountRequestDto("CURRENT", BigDecimal.valueOf(25000),"1");
        //execute
        Mockito.when(inputServiceMock.createAccount(accountRequestDto))
                .thenReturn(Mockito.any(AccountResponseDto.class));
        ResponseEntity<AccountResponseDto> response = underTest.createBankAccount(accountRequestDto);
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(inputServiceMock, Mockito.atLeast(1)).createAccount(accountRequestDto);
            Assertions.assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
        });
    }

    @Test
    void getAccountById() {
        //prepare
        final String accountId ="1";
        //execute
        Mockito.when(inputServiceMock.getAccountById(accountId))
                .thenReturn(Mockito.mock(AccountResponseDto.class));
        AccountResponseDto accountResponseDto = underTest.getAccountById(accountId);
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(inputServiceMock, Mockito.atLeast(1)).getAccountById(accountId);
            Assertions.assertNotNull(accountResponseDto);
        });
    }

    @Test
    void switchAccountState() {
        //prepare
        final String accountId ="1";
        //execute
        Mockito.when(inputServiceMock.switchAccountState(accountId))
                .thenReturn(Mockito.mock(AccountResponseDto.class));
        ResponseEntity<AccountResponseDto> responseEntity = underTest.switchAccountState(accountId);
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(inputServiceMock, Mockito.atLeast(1)).switchAccountState(accountId);
            Assertions.assertEquals(HttpStatusCode.valueOf(200),responseEntity.getStatusCode());
        });
    }

    @Test
    void updateAccountBalance() {
        //prepare
        final String accountId ="1";
        final BigDecimal amount = BigDecimal.valueOf(25000);
        //execute
        Mockito.when(inputServiceMock.updateAccountBalance(accountId,amount))
                .thenReturn(Mockito.mock(AccountResponseDto.class));
        ResponseEntity<AccountResponseDto> response = underTest.updateAccountBalance(accountId,amount);
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(inputServiceMock, Mockito.atLeast(1))
                    .updateAccountBalance(accountId,amount);
            Assertions.assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
        });
    }

    @Test
    void updateAccountOverdraftOrInterestRate() {
        //prepare
        final String accountId ="1";
        final double value = 5;
        //execute
        Mockito.when(inputServiceMock.updateAccountOverdraftOrInterestRate(accountId,value))
                .thenReturn(Mockito.mock(AccountResponseDto.class));
        ResponseEntity<AccountResponseDto> response = underTest.updateAccountOverdraftOrInterestRate(accountId,value);
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(inputServiceMock, Mockito.atLeast(1))
                    .updateAccountOverdraftOrInterestRate(accountId,value);
            Assertions.assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
        });
    }
}