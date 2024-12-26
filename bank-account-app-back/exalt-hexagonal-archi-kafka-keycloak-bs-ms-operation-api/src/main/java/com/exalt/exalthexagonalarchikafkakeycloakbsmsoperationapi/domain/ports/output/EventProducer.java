package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;

public interface EventProducer {
    void createOperationEvent(OperationEvent operationEvent);
}
