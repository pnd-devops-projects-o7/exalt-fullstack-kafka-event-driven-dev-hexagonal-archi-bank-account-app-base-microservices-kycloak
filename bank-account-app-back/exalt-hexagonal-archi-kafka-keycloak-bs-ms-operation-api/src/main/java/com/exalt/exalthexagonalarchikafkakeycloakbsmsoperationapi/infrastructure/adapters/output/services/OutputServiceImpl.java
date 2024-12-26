package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {
    private final OperationRepository operationRepository;
    @Override
    public void createOperation(OperationEntity operationEntity) {
        operationRepository.save(operationEntity);
    }

    @Override
    public Collection<OperationEntity> getAllOperations() {
        return operationRepository.findAll();
    }
}
