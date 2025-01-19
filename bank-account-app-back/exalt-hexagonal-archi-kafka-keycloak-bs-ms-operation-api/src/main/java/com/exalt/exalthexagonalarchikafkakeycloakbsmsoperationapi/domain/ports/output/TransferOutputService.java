package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.TransferEntity;

import java.util.Collection;

public interface TransferOutputService {
    void createTransfer(TransferEntity transferEntity);

    Collection<TransferEntity> getAllTransfers();

    TransferEntity getTransfer(String transferId);
}
