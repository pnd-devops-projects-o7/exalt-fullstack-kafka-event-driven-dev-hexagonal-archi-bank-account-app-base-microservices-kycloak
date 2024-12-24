package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;

public interface InputService {
    OperationResponseDto createOperation(OperationRequestDto operationRequestDto);
}
