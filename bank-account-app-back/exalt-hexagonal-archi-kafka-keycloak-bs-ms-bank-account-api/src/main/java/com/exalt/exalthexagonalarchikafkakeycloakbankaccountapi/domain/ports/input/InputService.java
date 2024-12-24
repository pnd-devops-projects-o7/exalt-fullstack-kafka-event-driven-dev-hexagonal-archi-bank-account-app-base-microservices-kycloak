package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;

import java.util.Collection;
import java.util.Map;

public interface InputService {
    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);
    Map<String, Map<String, String>> getWelcome();
    Collection<AccountResponseDto> getAllBankAccounts();
    AccountResponseDto getAccountById(String accountId);
    AccountResponseDto activateAccount(String accountId);
    AccountResponseDto suspendAccount(String accountId);
}
