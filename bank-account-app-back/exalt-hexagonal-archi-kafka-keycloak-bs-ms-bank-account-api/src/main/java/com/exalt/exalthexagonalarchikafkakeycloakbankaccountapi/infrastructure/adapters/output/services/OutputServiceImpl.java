package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.repositories.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {
    private final BankAccountRepository bankAccountRepository;
    @Override
    public void createCurrentAccount(CurrentAccount currentAccount) {
        bankAccountRepository.save(currentAccount);
    }

    @Override
    public void createSavingAccount(SavingAccount savingAccount) {
        bankAccountRepository.save(savingAccount);
    }

    @Override
    public Collection<BankAccountEntity> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccountEntity getAccountById(String accountId) {
        return bankAccountRepository.getAccountById(accountId);
    }
}
