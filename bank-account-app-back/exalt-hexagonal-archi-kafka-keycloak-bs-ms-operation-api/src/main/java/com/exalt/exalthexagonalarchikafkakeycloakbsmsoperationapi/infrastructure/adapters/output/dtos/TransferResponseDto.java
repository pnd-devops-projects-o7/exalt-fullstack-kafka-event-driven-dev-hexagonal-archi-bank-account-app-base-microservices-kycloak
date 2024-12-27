package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.AccountResponseDto;

import java.math.BigDecimal;
import java.time.Instant;

public record TransferResponseDto(String transferId, AccountResponseDto origin, AccountResponseDto destination, BigDecimal transferAmount,
                                  String description, Instant createdAt) {
}
