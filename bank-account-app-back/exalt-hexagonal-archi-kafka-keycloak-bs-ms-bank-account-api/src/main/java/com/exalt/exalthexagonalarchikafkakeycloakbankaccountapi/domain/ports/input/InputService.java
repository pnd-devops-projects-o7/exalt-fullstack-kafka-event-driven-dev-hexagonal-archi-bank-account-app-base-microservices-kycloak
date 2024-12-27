package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;

import java.math.BigDecimal;
import java.util.Collection;

public interface InputService {
    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);
    Collection<AccountResponseDto> getAllBankAccounts();
    AccountResponseDto getAccountById(String accountId);
    AccountResponseDto activateAccount(String accountId);
    AccountResponseDto suspendAccount(String accountId);
    AccountResponseDto updateAccountBalance(String accountId, BigDecimal amount);
    AccountResponseDto updateAccountInterestRate(String accountId, Double interestRate);
    AccountResponseDto updateAccountOverdraft(String accountId, Double overdraft);
}
