package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos;


import java.math.BigDecimal;

public record AccountRequestDto (String type, BigDecimal balance, String customerId){
}
