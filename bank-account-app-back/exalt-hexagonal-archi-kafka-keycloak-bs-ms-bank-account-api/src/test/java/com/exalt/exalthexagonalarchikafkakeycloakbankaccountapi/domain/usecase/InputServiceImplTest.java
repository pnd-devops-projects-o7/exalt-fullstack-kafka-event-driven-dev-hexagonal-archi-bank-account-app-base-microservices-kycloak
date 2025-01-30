package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.AccountNotFoundException;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.BankAccountApiBusinessException;
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

class InputServiceImplTest {
    @Mock
    private OutputService outputServiceMock;
    @Mock
    private RemoteClientService remoteClientServiceMock;
    @Mock
    private EventProducer eventProducerMock;
    private AutoCloseable autoCloseable;
    @InjectMocks
    private InputServiceImpl inputServiceImplUnderTest;
    final CustomerResponseDto customerResponseDto = new CustomerResponseDto(
            new CustomerDto("test", "test", "test", "test", "test",
                    Instant.now()), new AddressDto("test", 1, "test", 59300,
            "test", "test", "test"));
    final AccountRequestDto currentAccountRequestDto = new AccountRequestDto(
            "CURRENT", BigDecimal.valueOf(200), "11"
    );
    final AccountRequestDto savingAccountRequestDto = new AccountRequestDto(
            "SAVING", BigDecimal.valueOf(200), "12"
    );

    CurrentAccount currentAccount = new CurrentAccount(200);

    SavingAccount savingAccount = new SavingAccount(2.5);

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        currentAccount.setAccountId("11");
        currentAccount.setAccountState("ACTIVE");
        currentAccount.setBalance(BigDecimal.valueOf(2000));
        currentAccount.setCustomerId(currentAccount.getCustomerId());
        currentAccount.setCreatedAt(Instant.now());

        savingAccount.setAccountId("22");
        savingAccount.setAccountState("ACTIVE");
        savingAccount.setBalance(BigDecimal.valueOf(200000));
        savingAccount.setCustomerId(currentAccount.getCustomerId());
        savingAccount.setCreatedAt(Instant.now());
    }

    @AfterEach
    void setAutoCloseable() throws Exception {
        autoCloseable.close();
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
        Assertions.assertAll("*", () -> {
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1)).getRemoteCustomerById(currentAccountRequestDto.customerId());
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1)).getRemoteCustomerById(savingAccountRequestDto.customerId());
            Assertions.assertNotNull(currentAccountResponseDto);
            Assertions.assertNotNull(savingAccountResponseDto);
        });
    }

    @Test
    void getAllAccountTest() {
        //prepare
        Collection<BankAccountEntity> bankAccountEntities = List.of(currentAccount, savingAccount);
        //execute
        Mockito.when(outputServiceMock.getAllBankAccounts()).thenReturn(bankAccountEntities);
        Collection<AccountResponseDto> accountsResponseDto = inputServiceImplUnderTest.getAllBankAccounts();
        //verify
        Assertions.assertAll("*", () -> {
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAllBankAccounts();
            Assertions.assertNotNull(accountsResponseDto);
        });
    }

    @Test
    void getAccountByIdTest() {
        //prepare
        final String accountId = "11";
        //execute
        Mockito.when(outputServiceMock.getAccountById(accountId)).thenReturn(currentAccount);
        AccountResponseDto accountResponseDto = inputServiceImplUnderTest.getAccountById(accountId);
        Assertions.assertAll("", () -> {
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(accountId);
            Assertions.assertNotNull(accountResponseDto);
        });
    }

    @Test
    void switchActiveToSuspendAccountStateTest() {
        //prepare
        final String currentAccountId = "1111";
        final String savingAccountId = "2222";
        //execute
        Mockito.when(outputServiceMock.getAccountById(currentAccountId)).thenReturn(currentAccount);
        Mockito.when(outputServiceMock.getAccountById(savingAccountId)).thenReturn(savingAccount);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(currentAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(savingAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        AccountResponseDto accountResponseDto1 = inputServiceImplUnderTest.switchAccountState(currentAccountId);
        AccountResponseDto accountResponseDto2 = inputServiceImplUnderTest.switchAccountState(savingAccountId);
        //verify
        Assertions.assertAll("*", () -> {
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(currentAccountId);
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(savingAccountId);
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1))
                    .getRemoteCustomerById(currentAccount.getCustomerId());
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1))
                    .getRemoteCustomerById(savingAccount.getCustomerId());
            Assertions.assertNotNull(accountResponseDto1);
            Assertions.assertNotNull(accountResponseDto2);
        });
    }

    @Test
    void switchSuspendToActiveAccountStateTest() {
        //prepare
        final String currentAccountId = "1111";
        CurrentAccount suspendedCurrentAccount = CurrentAccount.builder()
                .overdraft(2000)
                .build();
        suspendedCurrentAccount.setAccountId(currentAccountId);
        suspendedCurrentAccount.setAccountState("SUSPENDED");
        suspendedCurrentAccount.setBalance(BigDecimal.valueOf(2000));
        suspendedCurrentAccount.setCustomerId(currentAccount.getCustomerId());
        suspendedCurrentAccount.setCreatedAt(Instant.now());

        final String savingAccountId = "2222";
        SavingAccount suspendedSavingAccount = SavingAccount.builder()
                .interestRate(2.5)
                .build();
        suspendedSavingAccount.setAccountId(savingAccountId);
        suspendedSavingAccount.setAccountState("SUSPENDED");
        suspendedSavingAccount.setBalance(BigDecimal.valueOf(2000));
        suspendedSavingAccount.setCustomerId(currentAccount.getCustomerId());
        suspendedSavingAccount.setCreatedAt(Instant.now());
        //execute
        Mockito.when(outputServiceMock.getAccountById(currentAccountId))
                .thenReturn(suspendedCurrentAccount);
        Mockito.when(outputServiceMock.getAccountById(savingAccountId))
                .thenReturn(savingAccount);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(suspendedCurrentAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(suspendedCurrentAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        AccountResponseDto currentAccountResponseDTo = inputServiceImplUnderTest.switchAccountState(currentAccountId);
        AccountResponseDto savingAccountResponseDTo = inputServiceImplUnderTest.switchAccountState(savingAccountId);
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(currentAccountResponseDTo);
            Assertions.assertNotNull(savingAccountResponseDTo);
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(currentAccountId);
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(savingAccountId);
        });
    }

    @Test
    void updateCurrentAccountBalanceTest() {
        //prepare
        final String currentAccountId = "1111";
        final BigDecimal newBalance = BigDecimal.valueOf(2500);
        //execute
        Mockito.when(outputServiceMock.getAccountById(currentAccountId)).thenReturn(currentAccount);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(currentAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        AccountResponseDto accountResponseDto = inputServiceImplUnderTest.updateAccountBalance(currentAccountId, newBalance);
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(accountResponseDto);
            Assertions.assertEquals(BigDecimal.valueOf(4500), accountResponseDto.balance());
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(currentAccountId);
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1))
                    .getRemoteCustomerById(currentAccount.getCustomerId());
        });
    }

    @Test
    void updateSavingAccountBalanceTest() {
        //prepare
        final String savingAccountId = "222";
        final BigDecimal newBalance = BigDecimal.valueOf(50000);

        //execute
        Mockito.when(outputServiceMock.getAccountById(savingAccountId)).thenReturn(savingAccount);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(savingAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        AccountResponseDto accountResponseDto = inputServiceImplUnderTest.updateAccountBalance(savingAccountId, newBalance);

        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(accountResponseDto);
            Assertions.assertEquals(BigDecimal.valueOf(250000), accountResponseDto.balance());
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(savingAccountId);
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1))
                    .getRemoteCustomerById(savingAccount.getCustomerId());
        });
    }

    @Test
    void updateAccountOverdraftOrInterestRate() {
        //prepare
        final String currentAccountId = "111";
        final String savingAccountId = "222";
        final double newOverdraft = 50;
        final double newIRate = 3.5;

        //execute
        Mockito.when(outputServiceMock.getAccountById(currentAccountId)).thenReturn(currentAccount);
        Mockito.when(outputServiceMock.getAccountById(savingAccountId)).thenReturn(savingAccount);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(currentAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        Mockito.when(remoteClientServiceMock.getRemoteCustomerById(savingAccount.getCustomerId()))
                .thenReturn(customerResponseDto);
        AccountResponseDto currentAccountResponseDto = inputServiceImplUnderTest.updateAccountOverdraftOrInterestRate(currentAccountId, newOverdraft);
        AccountResponseDto savingAccountResponseDto = inputServiceImplUnderTest.updateAccountOverdraftOrInterestRate(savingAccountId, newIRate);

        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(currentAccountResponseDto);
            Assertions.assertNotNull(savingAccountResponseDto);
            Assertions.assertEquals(50, currentAccount.getOverdraft());
            Assertions.assertEquals(3.5, savingAccount.getInterestRate());
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(currentAccountId);
            Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(savingAccountId);
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1))
                    .getRemoteCustomerById(savingAccount.getCustomerId());
            Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1))
                    .getRemoteCustomerById(currentAccount.getCustomerId());
        });
    }

    // test of business exceptions
    @Test
    void canReturnBankAccountApiBusinessException_EmptyFields() {
        //prepare
        final String exceptionMessage = "many fields are empty";
        BankAccountApiBusinessException runtimeException = Assertions
                .assertThrows(BankAccountApiBusinessException.class, () -> {
                    //prepare
                    final String customerId = "11";
                    AccountRequestDto accountRequestDtoInvalidFields = new AccountRequestDto("", null, customerId);
                    //execute
                    AccountResponseDto accountResponseDtoNull = inputServiceImplUnderTest.createAccount(accountRequestDtoInvalidFields);
                    //verify
                    Assertions.assertAll("*", () -> {
                        Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1)).getRemoteCustomerById(customerId);
                        Assertions.assertNull(accountResponseDtoNull);
                    });
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void canReturnBankAccountApiBusinessException_FirstDepositInvalid() {
        //prepare
        final String exceptionMessage = "the first deposit balance is not enough";
        BankAccountApiBusinessException runtimeException = Assertions
                .assertThrows(BankAccountApiBusinessException.class, () -> {
                    //prepare
                    final String customerId = "11";
                    AccountRequestDto accountRequestDtoInvalidFields = new AccountRequestDto("CURRENT", BigDecimal.TEN, customerId);
                    //execute
                    AccountResponseDto accountResponseDtoNull = inputServiceImplUnderTest.createAccount(accountRequestDtoInvalidFields);
                    //verify
                    Assertions.assertNull(accountResponseDtoNull);
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void canReturnBankAccountApiBusinessException_AccountTypeInvalid() {
        //prepare
        final String exceptionMessage = "account type provided invalid";
        BankAccountApiBusinessException runtimeException = Assertions
                .assertThrows(BankAccountApiBusinessException.class, () -> {
                    //prepare
                    final String customerId = "11";
                    AccountRequestDto accountRequestDtoInvalidType = new AccountRequestDto("INVALID", BigDecimal.valueOf(2500),
                            customerId);
                    //execute
                    AccountResponseDto accountResponseDto = inputServiceImplUnderTest.createAccount(accountRequestDtoInvalidType);
                    //verify
                    Assertions.assertNull(accountResponseDto);
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertEquals(exceptionMessage, runtimeException.getMessage());
        });
    }

    @Test
    void canReturnBankAccountApiBusinessException_RemoteCustomerUnreachable() {
        //prepare
        final String exceptionMessage = "customer client unreachable";
        BankAccountApiBusinessException runtimeException = Assertions.assertThrows(BankAccountApiBusinessException.class,
                () -> {
                    //prepare
                    final String customerId = "000";
                    AccountRequestDto accountRequestDto = new AccountRequestDto("CURRENT", BigDecimal.valueOf(2500),
                            customerId);
                    //execute
                    Mockito.when(remoteClientServiceMock.getRemoteCustomerById(customerId)).thenReturn(null);
                    AccountResponseDto accountResponseDto = inputServiceImplUnderTest.createAccount(accountRequestDto);
                    //verify
                    Assertions.assertAll("*", () -> {
                        Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1)).getRemoteCustomerById(customerId);
                        Assertions.assertNull(accountResponseDto);
                    });
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void canReturnBankAccountApiBusinessException_CustomerStateUnAllowed() {
        //prepare
        final String exceptionMessage = "customer state not allowed";
        BankAccountApiBusinessException runtimeException = Assertions
                .assertThrows(BankAccountApiBusinessException.class, () -> {
                    //prepare
                    final String customerId = "111";
                    CustomerResponseDto customerResponseDtoCopy = new CustomerResponseDto(
                            new CustomerDto("test", "test", "test", "test", "SUSPENDED",
                                    Instant.now()), null);
                    //execute
                    AccountRequestDto accountRequestDto = new AccountRequestDto("CURRENT", BigDecimal.valueOf(2500),
                            customerId);
                    Mockito.when(remoteClientServiceMock.getRemoteCustomerById(customerId)).thenReturn(customerResponseDtoCopy);
                    AccountResponseDto accountResponseDto = inputServiceImplUnderTest.createAccount(accountRequestDto);

                    //verify
                    Assertions.assertAll("**", () -> {
                        Assertions.assertNull(accountResponseDto);
                        Mockito.verify(remoteClientServiceMock, Mockito.atLeast(1)).getRemoteCustomerById(customerId);
                    });
                });
        //verify
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void canReturnAccountNotFound_updateBalance() {
        //prepare
        final String exceptionMessage = "account with id";
        AccountNotFoundException runtimeException = Assertions.assertThrows(AccountNotFoundException.class,
                () -> {
                    //prepare
                    final String accountId = "000";
                    //execute
                    Mockito.when(outputServiceMock.getAccountById(accountId)).thenReturn(null);
                    AccountResponseDto accountResponseDto = inputServiceImplUnderTest.updateAccountBalance(accountId, BigDecimal.valueOf(200));
                    //verify
                    Assertions.assertAll("*", () -> {
                        Assertions.assertNull(accountResponseDto);
                        Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(accountId);
                    });
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void canReturnAccountNotFound_switchState() {
        //prepare
        final String exceptionMessage = "not exist";
        AccountNotFoundException runtimeException = Assertions
                .assertThrows(AccountNotFoundException.class, () -> {
                    //prepare
                    final String accountId = "000";
                    //execute
                    Mockito.when(outputServiceMock.getAccountById(accountId)).thenReturn(null);
                    AccountResponseDto accountResponseDto = inputServiceImplUnderTest.switchAccountState(accountId);
                    //verify
                    Assertions.assertAll("*", () -> {
                        Assertions.assertNull(accountResponseDto);
                        Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(accountId);
                    });
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void canReturnBankAccountApiBusinessException_switchState_due_accountState() {
        //prepare
        final String exceptionMsg = "can not be activated/suspended because";
        BankAccountApiBusinessException runtimeException = Assertions
                .assertThrows(BankAccountApiBusinessException.class, () -> {
                    //prepare
                    final String currentAccountId = "21";
                    CurrentAccount currentAccount1 = new CurrentAccount(10);
                    currentAccount1.setAccountId(currentAccountId);
                    currentAccount1.setAccountState("CREATED");
                    currentAccount1.setBalance(BigDecimal.valueOf(300000));
                    currentAccount1.setCustomerId(currentAccount.getCustomerId());
                    currentAccount1.setCreatedAt(Instant.now());

                    final String savingAccountId = "23";
                    SavingAccount savingAccount1 = new SavingAccount(4.2);
                    savingAccount1.setAccountId(savingAccountId);
                    savingAccount1.setAccountState("CREATED");
                    savingAccount1.setBalance(BigDecimal.valueOf(350000));
                    savingAccount1.setCustomerId(savingAccount.getCustomerId());
                    savingAccount1.setCreatedAt(Instant.now());
                    //execute
                    Mockito.when(outputServiceMock.getAccountById(currentAccountId)).thenReturn(currentAccount1);
                    Mockito.when(outputServiceMock.getAccountById(savingAccountId)).thenReturn(savingAccount1);
                    AccountResponseDto currentAccountResponseDto = inputServiceImplUnderTest.switchAccountState(currentAccountId);
                    AccountResponseDto savingAccountResponseDto = inputServiceImplUnderTest.switchAccountState(savingAccountId);
                    //verify
                    Assertions.assertAll("*", () -> {
                        Assertions.assertNull(currentAccountResponseDto);
                        Assertions.assertNull(savingAccountResponseDto);
                        Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(currentAccountId);
                        Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(savingAccountId);
                    });
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionMsg));
        });
    }

    @Test
    void canReturnAccountNotFound_updateOverdraftOrIRate() {
        //prepare
        final String exceptionMsg = "account with id";
        AccountNotFoundException accountNotFoundException = Assertions
                .assertThrows(AccountNotFoundException.class, () -> {
                    //prepare
                    final String accountId = "00";
                    //execute
                    Mockito.when(outputServiceMock.getAccountById(accountId)).thenReturn(null);
                    AccountResponseDto accountResponseDtoNull = inputServiceImplUnderTest.updateAccountOverdraftOrInterestRate(accountId,2000.0);
                    //verify
                    Assertions.assertAll("*", () -> {
                        Assertions.assertNull(accountResponseDtoNull);
                        Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(accountId);
                    });
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(accountNotFoundException);
            Assertions.assertTrue(accountNotFoundException.getMessage().contains(exceptionMsg));
        });
    }

    @Test
    void canReturnBankAccountApiBusinessException_updateOverdraftOrIRate(){
        //prepare
        final String exceptionMsg ="in not updatable state";
        BankAccountApiBusinessException bankAccountApiBusinessException = Assertions
                .assertThrows(BankAccountApiBusinessException.class, ()->{
                    //prepare
                    final String currentAccountId = "21";
                    CurrentAccount currentAccount1 = new CurrentAccount(10);
                    currentAccount1.setAccountId(currentAccountId);
                    currentAccount1.setAccountState("CREATED");
                    currentAccount1.setBalance(BigDecimal.valueOf(300000));
                    currentAccount1.setCustomerId(currentAccount.getCustomerId());
                    currentAccount1.setCreatedAt(Instant.now());
                    //execute
                    Mockito.when(outputServiceMock.getAccountById(currentAccountId)).thenReturn(currentAccount1);
                    AccountResponseDto accountResponseDtoNull = inputServiceImplUnderTest.updateAccountOverdraftOrInterestRate(currentAccountId,0.0);
                    //verify
                    Assertions.assertAll("*", () -> {
                        Assertions.assertNull(accountResponseDtoNull);
                        Mockito.verify(outputServiceMock, Mockito.atLeast(1)).getAccountById(currentAccountId);
                    });
                });
        //verify
        Assertions.assertAll("*", () -> {
            Assertions.assertNotNull(bankAccountApiBusinessException);
            Assertions.assertTrue(bankAccountApiBusinessException.getMessage().contains(exceptionMsg));
        });
    }
}