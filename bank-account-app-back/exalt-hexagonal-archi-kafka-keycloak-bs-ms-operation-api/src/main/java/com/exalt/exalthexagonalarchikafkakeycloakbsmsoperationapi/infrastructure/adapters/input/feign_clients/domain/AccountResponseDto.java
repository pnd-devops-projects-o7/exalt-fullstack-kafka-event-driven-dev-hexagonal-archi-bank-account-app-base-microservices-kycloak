package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain;


import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponseDto (String accountId, String accountType, String accountState, BigDecimal balance,
                                  Instant createdAt, CustomerResponseDto customerResponseDto){
}
