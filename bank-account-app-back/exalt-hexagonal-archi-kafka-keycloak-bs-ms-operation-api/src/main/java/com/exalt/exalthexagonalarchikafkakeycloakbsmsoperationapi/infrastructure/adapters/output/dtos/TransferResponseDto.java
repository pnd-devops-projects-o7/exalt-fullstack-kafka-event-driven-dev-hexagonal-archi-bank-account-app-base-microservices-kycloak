package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;

import java.math.BigDecimal;
import java.time.Instant;

public record TransferResponseDto(String transferId, BigDecimal transferAmount, String description, Instant createdAt,AccountResponseDto origin, AccountResponseDto destination) {
}
