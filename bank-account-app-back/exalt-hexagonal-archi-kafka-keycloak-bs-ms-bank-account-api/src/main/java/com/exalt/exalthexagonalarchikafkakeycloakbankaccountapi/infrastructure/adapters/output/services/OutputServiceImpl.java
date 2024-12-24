package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.repositories.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {
    private final BankAccountRepository bankAccountRepository;
    @Value("${application.welcome-message}")
    private String welcome;
    @Value("${application.managed-operations.operation1}")
    private String op1;
    @Value("${application.managed-operations.operation2}")
    private String op2;
    @Value("${application.managed-operations.operation3}")
    private String op3;
    @Value("${application.managed-operations.operation4}")
    private String op4;
    @Override
    public void createCurrentAccount(CurrentAccount currentAccount) {
        bankAccountRepository.save(currentAccount);
    }

    @Override
    public void createSavingAccount(SavingAccount savingAccount) {
        bankAccountRepository.save(savingAccount);
    }

    @Override
    public Map<String, Map<String, String>> getWelcome() {
        return Map.of(welcome,
                Map.of("operation1",op1, "operation2", op2,"operation3", op3, "operation4", op4));
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
