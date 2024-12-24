package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.feign_client.domain.CustomerResponseDto;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponseDto(String accountId, String accountType, String accountState, BigDecimal balance,
                                 Instant createdAt, CustomerResponseDto customerResponseDto) {
}