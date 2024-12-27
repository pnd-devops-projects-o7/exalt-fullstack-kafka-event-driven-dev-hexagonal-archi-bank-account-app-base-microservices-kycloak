package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;

public interface EventProducer {
    void createOperationEvent(OperationEvent operationEvent);
    void createTransfer(TransferEvent transferEvent);
}
