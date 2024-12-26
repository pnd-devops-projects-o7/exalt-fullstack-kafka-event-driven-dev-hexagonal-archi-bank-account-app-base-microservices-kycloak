package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos;



import java.math.BigDecimal;

public record OperationRequestDto(String operationType, BigDecimal transactionAmount, String description, String accountId) { }
