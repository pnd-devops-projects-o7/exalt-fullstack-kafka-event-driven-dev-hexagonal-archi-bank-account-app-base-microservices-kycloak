package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OperationOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.TransferOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.TransferEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.repositories.OperationRepository;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OutputServiceImpl implements OperationOutputService, TransferOutputService {
    private final OperationRepository operationRepository;
    private final TransferRepository transferRepository;
    @Override
    public void createOperation(OperationEntity operationEntity) {
        operationRepository.save(operationEntity);
    }

    @Override
    public Collection<OperationEntity> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public OperationEntity getOperationById(String operationId) {
        return operationRepository.getOperationById(operationId);
    }

    @Override
    public void createTransfer(TransferEntity transferEntity) {
        transferRepository.save(transferEntity);
    }

    @Override
    public Collection<TransferEntity> getAllTransfers() {
        return transferRepository.findAll();
    }
}
