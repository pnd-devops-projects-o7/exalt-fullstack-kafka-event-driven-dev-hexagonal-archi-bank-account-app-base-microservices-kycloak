package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;

import java.math.BigDecimal;

public class ValidatorTools {
    private static final String SAVING_ACCOUNT="SAVING";
    private static final String CURRENT_ACCOUNT="CURRENT";
    private ValidatorTools() {
    }

    //operation validators
    public static boolean operationRequestDtoFieldsEmpty(OperationRequestDto operationRequestDto) {
        return operationRequestDto.operationType().isBlank()
                || operationRequestDto.description().isBlank()
                || operationRequestDto.accountId().isBlank();
    }

    public static boolean remoteAccountClientUnreachable(AccountResponseDto accountResponseDto) {
        return accountResponseDto == null;
    }

    public static boolean remoteAccountStateUnAllowed(String state) {
        return state.equals("CREATED") || state.equals("SUSPENDED");
    }

    public static boolean remoteAccountTypeUnAllowed(String accountType) {
        return accountType.equals(SAVING_ACCOUNT);
    }

    public static boolean operationTypeValid(String operationType) {
        return operationType.equals("DEPOSIT") || operationType.equals("WITHDRAW");
    }

    public static boolean accountBalanceInsufficient(AccountResponseDto accountResponseDto,BigDecimal operationAmount) {
        int result=10000;
        if(accountResponseDto.accountType().equals(CURRENT_ACCOUNT)) {
            result = accountResponseDto.balance()
                    .add(BigDecimal.valueOf(accountResponseDto.overdraft()))
                    .compareTo(operationAmount);
        }
        else if(accountResponseDto.accountType().equals(SAVING_ACCOUNT)){
            result = accountResponseDto.balance()
                    .compareTo(operationAmount);
        }
        return result < 0;
    }

    //transfer validators
    public static boolean transferRequestDtoFieldsEmpty(TransferRequestDto transferRequestDto){
        return transferRequestDto.originAccount().isBlank()
                || transferRequestDto.destinationAccount().isBlank()
                || transferRequestDto.transferAmount()==null
                || transferRequestDto.description().isBlank();
    }
}
