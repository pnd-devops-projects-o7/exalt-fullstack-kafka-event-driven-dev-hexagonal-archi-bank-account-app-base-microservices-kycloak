package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;

import java.math.BigDecimal;
import java.util.Collection;

public interface InputService {
    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);
    Collection<AccountResponseDto> getAllBankAccounts();
    AccountResponseDto getAccountById(String accountId);
    AccountResponseDto switchAccountState(String accountId);
    AccountResponseDto updateAccountBalance(String accountId, BigDecimal amount);
    AccountResponseDto updateAccountOverdraftOrInterestRate(String accountId, Double overdraftOrIrate);
}
