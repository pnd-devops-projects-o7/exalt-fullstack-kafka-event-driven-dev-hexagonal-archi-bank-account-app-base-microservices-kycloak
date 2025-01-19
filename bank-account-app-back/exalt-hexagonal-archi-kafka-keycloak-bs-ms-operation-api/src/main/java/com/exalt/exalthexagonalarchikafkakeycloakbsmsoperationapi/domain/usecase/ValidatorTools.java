package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;

import java.math.BigDecimal;

public class ValidatorTools {
    private ValidatorTools() {
    }

    //operation validators
    public static boolean operationRequestDtoFieldsEmpty(OperationRequestDto operationRequestDto) {
        return operationRequestDto.operationType().isBlank()
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

    public static boolean remoteAccountStateUnAllowed(String state) {
        return state.equals("CREATED") || state.equals("SUSPENDED");
    }

    public static boolean remoteAccountTypeUnAllowed(String accountType) {
        return accountType.equals("SAVING");
    }

    public static boolean operationTypeValid(String operationType) {
        return operationType.equals("DEPOSIT") || operationType.equals("WITHDRAW");
    }

    public static boolean accountBalanceInsufficient(AccountResponseDto accountResponseDto,BigDecimal operationAmount) {
        int result = accountResponseDto.balance()
                .add(BigDecimal.valueOf(accountResponseDto.overdraft()))
                .compareTo(operationAmount);
        return result < 0;
    }
    public static boolean accountBalanceInsufficient(BigDecimal balance, BigDecimal transferAmount){
        return balance.compareTo(transferAmount)<0;
    }

    //transfer validators
    public static boolean transferRequestDtoFieldsEmpty(TransferRequestDto transferRequestDto){
        return transferRequestDto.originAccount().isBlank()
                || transferRequestDto.destinationAccount().isBlank()
                || transferRequestDto.transferAmount()==null
                || transferRequestDto.description().isBlank();
    }
}
