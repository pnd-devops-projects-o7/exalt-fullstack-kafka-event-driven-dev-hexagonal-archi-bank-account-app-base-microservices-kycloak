package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;

import java.util.Collection;

public interface OutputService {
    void createOperation(OperationEntity operationEntity);
    Collection<OperationEntity> getAllOperations();
}
