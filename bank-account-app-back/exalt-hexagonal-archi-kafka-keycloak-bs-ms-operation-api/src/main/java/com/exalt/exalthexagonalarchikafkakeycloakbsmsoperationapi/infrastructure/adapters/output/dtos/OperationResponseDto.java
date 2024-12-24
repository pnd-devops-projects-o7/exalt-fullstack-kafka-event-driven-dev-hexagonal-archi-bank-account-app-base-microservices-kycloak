package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.AccountResponseDto;

import java.math.BigDecimal;
import java.time.Instant;

public record OperationResponseDto (String operationId, String type, BigDecimal amount, String description,
                                    Instant createdAt, AccountResponseDto accountResponseDto){ }
