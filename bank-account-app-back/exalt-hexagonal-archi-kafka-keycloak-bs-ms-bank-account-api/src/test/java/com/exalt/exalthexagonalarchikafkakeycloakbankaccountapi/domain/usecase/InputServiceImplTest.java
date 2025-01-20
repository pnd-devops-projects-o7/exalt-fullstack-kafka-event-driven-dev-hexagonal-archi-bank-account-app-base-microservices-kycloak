package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.services.RemoteClientService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;
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

class InputServiceImplTest {
    @Mock
    private OutputService outputServiceMock;
    @Mock
    private RemoteClientService remoteClientServiceMock;
    @Mock
    private EventProducer eventProducerMock;
    @InjectMocks
    private  InputServiceImpl inputServiceImplUnderTest;
    final CustomerResponseDto customerResponseDto = new CustomerResponseDto(
            new CustomerDto("test","test","test","test","test",
                    Instant.now()), new AddressDto("test",1,"test",59300,
            "test","test","test"));
    final AccountRequestDto currentAccountRequestDto = new AccountRequestDto(
            "CURRENT", BigDecimal.valueOf(200),"11L"
    );
    final AccountRequestDto savingAccountRequestDto = new AccountRequestDto(
            "SAVING", BigDecimal.valueOf(200),"22L"
    );
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccountTest() {
        //execute
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(currentAccountRequestDto.customerId()))
                .thenReturn(customerResponseDto);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(savingAccountRequestDto.customerId()))
                .thenReturn(customerResponseDto);
        AccountResponseDto currentAccountResponseDto = inputServiceImplUnderTest.createAccount(currentAccountRequestDto);
        AccountResponseDto savingAccountResponseDto = inputServiceImplUnderTest.createAccount(savingAccountRequestDto);
        //verify
        Assertions.assertAll("",()->{
            Mockito.verify(remoteClientServiceMock).getRemoteCustomerById(currentAccountRequestDto.customerId());
            Mockito.verify(remoteClientServiceMock).getRemoteCustomerById(savingAccountRequestDto.customerId());
            Assertions.assertNotNull(currentAccountResponseDto);
            Assertions.assertNotNull(savingAccountResponseDto);
        });
    }
    @Test
    void getAllAccountTest(){
        //prepare
        CurrentAccount currentAccount= new CurrentAccount(200);
        currentAccount.setAccountId("11L");
        currentAccount.setAccountState("ACTIVE");
        currentAccount.setBalance(BigDecimal.valueOf(2000));
        currentAccount.setCustomerId(currentAccount.getCustomerId());
        currentAccount.setCreatedAt(Instant.now());

        SavingAccount savingAccount= new SavingAccount(2.5);
        savingAccount.setAccountId("22L");
        savingAccount.setAccountState("ACTIVE");
        savingAccount.setBalance(BigDecimal.valueOf(2000));
        savingAccount.setCustomerId(currentAccount.getCustomerId());
        savingAccount.setCreatedAt(Instant.now());

        Collection<BankAccountEntity> bankAccountEntities = List.of(currentAccount, savingAccount);
        //execute
        Mockito.when(outputServiceMock.getAllBankAccounts()).thenReturn(bankAccountEntities);
        Collection<AccountResponseDto> accountsResponseDto = inputServiceImplUnderTest.getAllBankAccounts();
        //verify
        Assertions.assertAll("",()->{
            Mockito.verify(outputServiceMock).getAllBankAccounts();
            Assertions.assertNotNull(accountsResponseDto);
        });
    }
    @Test
    void getAccountByIdTest(){
        //prepare
        final String accountId ="11L";
        CurrentAccount currentAccount= new CurrentAccount(200);
        currentAccount.setAccountId(accountId);
        currentAccount.setAccountState("ACTIVE");
        currentAccount.setBalance(BigDecimal.valueOf(2000));
        currentAccount.setCustomerId(currentAccount.getCustomerId());
        currentAccount.setCreatedAt(Instant.now());
        //execute
        Mockito.when(outputServiceMock.getAccountById(accountId)).thenReturn(currentAccount);
        AccountResponseDto accountResponseDto = inputServiceImplUnderTest.getAccountById(accountId);
        Assertions.assertAll("",()->{
            Mockito.verify(outputServiceMock).getAccountById(accountId);
            Assertions.assertNotNull(accountResponseDto);
        });
    }
 }