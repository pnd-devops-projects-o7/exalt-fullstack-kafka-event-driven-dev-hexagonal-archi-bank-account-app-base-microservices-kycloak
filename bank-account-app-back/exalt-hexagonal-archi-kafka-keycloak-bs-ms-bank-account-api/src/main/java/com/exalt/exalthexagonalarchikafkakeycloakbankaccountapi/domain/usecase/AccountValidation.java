package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;

import java.math.BigDecimal;

public class AccountValidation {
    private AccountValidation() {
    }

    public static boolean accountFieldsEmpty(AccountRequestDto accountRequestDto) {
        return accountRequestDto.type().isBlank() || accountRequestDto.balance() == null || accountRequestDto.customerId().isBlank();
    }

    public static boolean validFirstDeposit(BigDecimal firstDeposit) {
        int comparisonResult = firstDeposit.compareTo(new BigDecimal(50));
        return comparisonResult == 0 || comparisonResult > 0;
    }

    public static boolean remoteClientApiUnreachable(CustomerResponseDto customerResponseDto) {
        return customerResponseDto == null;
    }

    public static boolean remoteCustomerStateUnAllowed(CustomerResponseDto customerResponseDto) {
        return customerResponseDto.customerDto()
                .customerState()
                .equals("ARCHIVE")
                ||
                customerResponseDto.customerDto()
                        .customerState()
                        .equals("SUSPENDED");
    }

    public static boolean isValidAccountType(String accountType){
        return accountType.equals("CURRENT") || accountType.equals("SAVING");
    }
}
