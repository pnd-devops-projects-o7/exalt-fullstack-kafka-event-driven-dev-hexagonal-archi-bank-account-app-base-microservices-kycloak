package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.Operation;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services.RemoteAccountService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services.RemoteCustomerService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputServiceImpl implements InputService {
    private final RemoteAccountService remoteAccountService;
    private final RemoteCustomerService remoteCustomerService;
    private final OutputService outputService;
    private final EventProducer eventProducer;
    private static final String WITHDRAW = "WITHDRAW";
    private static final String DEPOSIT = "DEPOSIT";
    private static final Logger LOGGER = Logger.getLogger(InputServiceImpl.class.getSimpleName());

    public InputServiceImpl(RemoteAccountService remoteAccountService, RemoteCustomerService remoteCustomerService,
                            OutputService outputService, EventProducer eventProducer) {
        this.remoteAccountService = remoteAccountService;
        this.remoteCustomerService = remoteCustomerService;
        this.outputService = outputService;
        this.eventProducer = eventProducer;
    }

    @Override
    public OperationResponseDto createOperation(OperationRequestDto operationRequestDto) {
        //list of checks for payload validity
        if (OperationValidation.operationRequestDtoFieldsEmpty(operationRequestDto)) {
            throw new InputFieldsInvalidException("one or more fields are empty");
        }
        if (!OperationValidation.transactionAmountValid(operationRequestDto.transactionAmount())) {
            throw new TransactionAmountInvalidException("operation transaction amount invalid");
        }

        if (!OperationValidation.operationTypeValid(operationRequestDto.operationType())) {
            throw new OperationTypeInvalidException("operation type not valid");
        }

        AccountResponseDto accountResponseDto = remoteAccountService.getRemoteAccountById(operationRequestDto.accountId());

        //list of checks remote account
        if (OperationValidation.remoteAccountClientUnreachable(accountResponseDto)) {
            throw new RemoteAccountUnreachableException("remote account unreachable");
        }
        if (OperationValidation.remoteAccountClientStateUnAllowed(accountResponseDto.accountState())) {
            throw new RemoteAccountStateUnAllowedException("remote account state not allowed");
        }
        if (OperationValidation.remoteAccountTypeUnAllowed(accountResponseDto.accountType())) {
            throw new RemoteAccountTypeUnAllowedException("remote account type not allowed");
        }

        switch (operationRequestDto.operationType()) {
            case WITHDRAW -> {
                if (!OperationValidation.accountBalanceEnough(accountResponseDto.balance(),
                        accountResponseDto.overdraft(), operationRequestDto.transactionAmount())) {
                    throw new RemoteAccountBalanceInsufficientException("remote account balance insufficient");
                }
                BigDecimal minusAmount = operationRequestDto.transactionAmount().negate();
                remoteAccountService.updateAccountBalance(accountResponseDto.accountId(), minusAmount);
            }
            case DEPOSIT -> remoteAccountService
                    .updateAccountBalance(accountResponseDto.accountId(), operationRequestDto.transactionAmount());
            default -> LOGGER.log(Level.INFO, "do nothing");
        }

        CustomerResponseDto customerResponseDto = remoteCustomerService
                .getRemoteCustomerById(accountResponseDto.customerResponseDto().customerDto().customerId());

        Operation operation = MapperService.mapFromOperationRequestDto(operationRequestDto);
        BankAccount bankAccount = MapperService.mapFromAccountResponseDto(accountResponseDto);
        bankAccount.setCustomer(MapperService.mapFromCustomerResponseDto(customerResponseDto));
        operation.setBankAccount(bankAccount);
        OperationEntity operationEntity = MapperService.mapFromOperation(operation);
        //call output connector to register operation entity
        outputService.createOperation(operationEntity);
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
        Collection<OperationEntity> operationEntities = outputService.getAllOperations();
        return operationEntities.stream().map(operationEntity -> {
            AccountResponseDto accountResponseDto = remoteAccountService.getRemoteAccountById(operationEntity.getAccountId());
            return MapperService.mapToOperationResponseDto(operationEntity, accountResponseDto);
        }).toList();
    }
}
