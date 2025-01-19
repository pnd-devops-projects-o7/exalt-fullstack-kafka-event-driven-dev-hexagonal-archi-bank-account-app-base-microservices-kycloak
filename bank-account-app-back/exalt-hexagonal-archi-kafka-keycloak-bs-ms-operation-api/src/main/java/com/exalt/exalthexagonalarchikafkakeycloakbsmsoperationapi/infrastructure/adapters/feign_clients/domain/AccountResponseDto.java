package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.Instant;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AccountResponseDto (String accountId, String accountType, String accountState, BigDecimal balance,
                                  Double overdraft, Double interestRate,Instant createdAt, CustomerResponseDto customerResponseDto){
}