package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;

import java.math.BigDecimal;
import java.time.Instant;

public record OperationResponseDto (String operationId, String operationType, BigDecimal transactionAmount, String description,
                                    Instant createdAt, AccountResponseDto accountResponseDto){ }
