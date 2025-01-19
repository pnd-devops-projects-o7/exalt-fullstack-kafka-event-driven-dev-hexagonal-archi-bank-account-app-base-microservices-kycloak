package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;

import java.math.BigDecimal;
import java.util.Collection;

public interface OperationInputService {
    OperationResponseDto createOperation(OperationRequestDto operationRequestDto, BigDecimal transactionAmount);
    Collection<OperationResponseDto> getAllOperations();
    OperationResponseDto getOperationById(String operationId);
}
