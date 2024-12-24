package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;

import java.util.Collection;
import java.util.Map;

public interface OutputService {
    void createCurrentAccount(CurrentAccount currentAccount);
    void createSavingAccount (SavingAccount savingAccount);
    Map<String, Map<String, String>> getWelcome();

    Collection<BankAccountEntity> getAllBankAccounts();

    BankAccountEntity getAccountById(String accountId);
}
