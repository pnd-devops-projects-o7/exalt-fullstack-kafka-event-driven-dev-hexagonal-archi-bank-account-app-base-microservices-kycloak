package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.Operation;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.OperationInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OperationOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteAccountService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteCustomerService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationInputServiceImpl implements OperationInputService {
    private final RemoteAccountService remoteAccountService;
    private final RemoteCustomerService remoteCustomerService;
    private final OperationOutputService operationOutputService;
    private final EventProducer eventProducer;
    private static final String WITHDRAW = "WITHDRAW";
    private static final String DEPOSIT = "DEPOSIT";
    private static final Logger log = Logger.getLogger(OperationInputServiceImpl.class.getSimpleName());

    public OperationInputServiceImpl(final RemoteAccountService remoteAccountService, final RemoteCustomerService remoteCustomerService,
                                     final OperationOutputService operationOutputService, final EventProducer eventProducer) {
        this.remoteAccountService = remoteAccountService;
        this.remoteCustomerService = remoteCustomerService;
        this.operationOutputService = operationOutputService;
        this.eventProducer = eventProducer;
    }

    @Override
    public OperationResponseDto createOperation(OperationRequestDto operationRequestDto, BigDecimal transactionAmount) {

        //list of checks for payload validity
        checkPayloadValidity(operationRequestDto, transactionAmount);
        AccountResponseDto accountResponseDto = remoteAccountService.getRemoteAccountById(operationRequestDto.accountId());
        //list of checks remote account
        checkRemoteAccount(accountResponseDto);

        switch (operationRequestDto.operationType()) {
            case WITHDRAW -> {
                if (ValidatorTools.accountBalanceInsufficient(accountResponseDto, transactionAmount)) {
                    throw new OperationApiBusinessException("remote account balance insufficient");
                }
                BigDecimal minusAmount = transactionAmount.negate();
                remoteAccountService.updateBalanceCurrentAccount(accountResponseDto.accountId(), minusAmount);
            }
            case DEPOSIT -> remoteAccountService
                    .updateBalanceCurrentAccount(accountResponseDto.accountId(), transactionAmount);
            default -> log.log(Level.INFO, "do nothing");
        }

        CustomerResponseDto customerResponseDto = remoteCustomerService
                .getRemoteCustomerById(accountResponseDto.customerResponseDto().customerDto().customerId());

        Operation operation = MapperService.mapFromOperationRequestDto(operationRequestDto, transactionAmount);
        BankAccount bankAccount = (BankAccount) MapperService.mapFromAccountResponseDto(accountResponseDto);
        bankAccount.setCustomer(MapperService.mapFromCustomerResponseDto(customerResponseDto));
        operation.setBankAccount(bankAccount);
        OperationEntity operationEntity = MapperService.mapFromOperation(operation);
        //call output connector to register operation entity
        operationOutputService.createOperation(operationEntity);
        //build kafka event
        OperationEvent operationEvent = OperationEvent.newBuilder()
                .setOperation(operation)
                .setStatus("OPERATION-CREATE")
                .setMessage("Creating operation")
                .build();
        // call output connector to send operation event to kafka infrastructure
        eventProducer.createOperationEvent(operationEvent);
        //build operation response dto
        return MapperService.mapToOperationResponseDto(operationEntity, accountResponseDto);
    }

    @Override
    public Collection<OperationResponseDto> getAllOperations() {
        Collection<OperationEntity> operationEntities = operationOutputService.getAllOperations();
        return operationEntities.stream().map(operationEntity -> {
            AccountResponseDto accountResponseDto = remoteAccountService.getRemoteAccountById(operationEntity.getAccountId());
            return MapperService.mapToOperationResponseDto(operationEntity, accountResponseDto);
        }).toList();
    }

    @Override
    public OperationResponseDto getOperationById(String operationId) {
        OperationEntity operationEntity = operationOutputService.getOperationById(operationId);
        if(operationEntity==null){
            throw new OperationNotFoundException(String.format("operation with id %s not found", operationId));
        }
        AccountResponseDto accountResponseDto = remoteAccountService.getRemoteAccountById(operationEntity.getAccountId());
        return MapperService.mapToOperationResponseDto(operationEntity,accountResponseDto);
    }

    //list of checks for payload validity
    private void checkPayloadValidity(OperationRequestDto operationRequestDto, BigDecimal transactionAmount){
        if (ValidatorTools.operationRequestDtoFieldsEmpty(operationRequestDto)) {
            throw new OperationApiBusinessException("one or more fields are empty");
        }
        if (transactionAmount==null) {
            throw new OperationApiBusinessException("operation transaction amount invalid");
        }

        if (!ValidatorTools.operationTypeValid(operationRequestDto.operationType())) {
            throw new OperationApiBusinessException("operation type not valid");
        }
    }

    //list of checks remote account
    private void checkRemoteAccount(AccountResponseDto accountResponseDto){

        if (ValidatorTools.remoteAccountClientUnreachable(accountResponseDto)) {
            throw new OperationApiBusinessException("remote account unreachable");
        }
        if (ValidatorTools.remoteAccountStateUnAllowed(accountResponseDto.accountState())) {
            throw new OperationApiBusinessException("remote account state not allowed");
        }
        if (ValidatorTools.remoteAccountTypeUnAllowed(accountResponseDto.accountType())) {
            throw new OperationApiBusinessException("remote account type not allowed");
        }
    }

}
