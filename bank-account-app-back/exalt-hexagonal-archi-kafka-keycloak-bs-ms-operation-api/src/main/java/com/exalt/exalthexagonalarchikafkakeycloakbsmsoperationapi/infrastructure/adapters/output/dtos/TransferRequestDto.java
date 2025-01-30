package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos;

import java.math.BigDecimal;

public record TransferRequestDto(String originAccount, String destinationAccount, BigDecimal transferAmount, String description) {
}
