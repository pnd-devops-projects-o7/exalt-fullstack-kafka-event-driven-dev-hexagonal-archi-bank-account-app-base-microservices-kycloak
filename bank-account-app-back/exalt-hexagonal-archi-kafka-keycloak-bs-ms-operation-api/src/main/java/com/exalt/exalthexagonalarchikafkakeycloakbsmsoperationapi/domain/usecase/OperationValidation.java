package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;

import java.math.BigDecimal;

public class OperationValidation {
    private OperationValidation() {
    }

    public static boolean operationRequestDtoFieldsEmpty(OperationRequestDto operationRequestDto) {
        return operationRequestDto.operationType().isBlank()
                || operationRequestDto.transactionAmount() == null
                || operationRequestDto.description().isBlank()
                || operationRequestDto.accountId().isBlank();
    }

    public static boolean transactionAmountValid(BigDecimal transactionAmount) {
        int comparison = transactionAmount.compareTo(new BigDecimal(10));
        return comparison == 0 || comparison > 0;
    }

    public static boolean remoteAccountClientUnreachable(AccountResponseDto accountResponseDto) {
        return accountResponseDto == null;
    }

    public static boolean remoteAccountClientStateUnAllowed(String state) {
        return state.equals("CREATED") || state.equals("SUSPENDED");
    }

    public static boolean remoteAccountTypeUnAllowed(String accountType) {
        return accountType.equals("SAVING");
    }

    public static boolean operationTypeValid(String operationType) {
        return operationType.equals("DEPOSIT") || operationType.equals("WITHDRAW");
    }

    public static boolean accountBalanceEnough(BigDecimal balance, Double overdraft,BigDecimal bigDecimal) {
        int comparison = balance.add(BigDecimal.valueOf(overdraft))
                .compareTo(bigDecimal);
        return comparison == 0 || comparison > 0;
    }
}
