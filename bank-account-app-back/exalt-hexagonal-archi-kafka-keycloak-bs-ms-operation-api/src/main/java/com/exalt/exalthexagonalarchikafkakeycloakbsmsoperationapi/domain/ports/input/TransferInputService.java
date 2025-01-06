package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferResponseDto;

import java.util.Collection;

public interface TransferInputService {
    TransferResponseDto createTransfer(TransferRequestDto transferRequestDto);

    Collection<TransferResponseDto> getAllTransfers();

    TransferResponseDto getTransfer(String transferId);
}
