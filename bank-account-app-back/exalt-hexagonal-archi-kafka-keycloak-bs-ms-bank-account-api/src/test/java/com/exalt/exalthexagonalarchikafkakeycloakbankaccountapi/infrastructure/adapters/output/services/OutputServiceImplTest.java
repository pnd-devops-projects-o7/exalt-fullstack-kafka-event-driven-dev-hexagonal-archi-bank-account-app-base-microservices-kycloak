package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.repositories.BankAccountRepository;
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

class OutputServiceImplTest {
    private AutoCloseable autoCloseable;
    @InjectMocks
    private OutputServiceImpl underTest;
    @Mock
    private BankAccountRepository bankAccountRepository;
    CurrentAccount currentAccount = new CurrentAccount(200);

    SavingAccount savingAccount = new SavingAccount(2.5);

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();

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

    @Test
    void createCurrentAccount() {
        //execute
        Mockito.when(bankAccountRepository.save(currentAccount)).thenReturn(currentAccount);
        underTest.createCurrentAccount(currentAccount);
        //verify
        Mockito.verify(bankAccountRepository, Mockito.atLeast(1)).save(currentAccount);

    }

    @Test
    void createSavingAccount() {
        //execute
        Mockito.when(bankAccountRepository.save(savingAccount)).thenReturn(savingAccount);
        underTest.createSavingAccount(savingAccount);
        //verify
        Mockito.verify(bankAccountRepository, Mockito.atLeast(1)).save(savingAccount);
    }

    @Test
    void getAllBankAccounts() {
        //prepare
        List<BankAccountEntity> bankAccountEntities = List.of(currentAccount, savingAccount);
        //execute
        Mockito.when(bankAccountRepository.findAllCreatedAccounts()).thenReturn(bankAccountEntities);
        underTest.getAllBankAccounts();
        //verify
        Mockito.verify(bankAccountRepository, Mockito.atLeast(1)).findAllCreatedAccounts();
    }

    @Test
    void getAccountById() {
        //prepare
        final String accountId ="11";
        //execute
        Mockito.when(bankAccountRepository.findAccountById(accountId)).thenReturn(currentAccount);
        BankAccountEntity bankAccountEntity = underTest.getAccountById(accountId);
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(bankAccountRepository, Mockito.atLeast(1)).findAccountById(accountId);
            Assertions.assertNotNull(bankAccountEntity);
        });
    }

    @Test
    void getAllAccountsByStateCreated() {
        //execute
        Mockito.when(bankAccountRepository.findAllAccountsByStateCreated()).thenReturn(null);
        Collection<BankAccountEntity> bankAccountEntities = underTest.getAllAccountsByStateCreated();
        //verify
        Assertions.assertAll("**",()->{
            Mockito.verify(bankAccountRepository, Mockito.atLeast(1)).findAllAccountsByStateCreated();
            Assertions.assertNull(bankAccountEntities);
        });
    }
}