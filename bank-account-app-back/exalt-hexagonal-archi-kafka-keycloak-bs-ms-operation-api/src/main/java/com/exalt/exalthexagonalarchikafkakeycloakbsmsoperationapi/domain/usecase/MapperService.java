package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.Operation;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.BankAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.dependencies.Customer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class MapperService {
    private MapperService(){}

    public static Operation mapFromOperationRequestDto(OperationRequestDto operationRequestDto, BigDecimal transactionAmount) {
        Operation operation = new Operation();
        operation.setOperationId(UUID.randomUUID().toString());
        operation.setOperationType(operationRequestDto.operationType());
        operation.setTransactionAmount(transactionAmount);
        operation.setCreatedAt(Instant.now());
        operation.setDescription(operationRequestDto.description());
        operation.setBankAccount(null);
        return operation;
    }

    public static BankAccount mapFromAccountResponseDto(AccountResponseDto accountResponseDto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountId(accountResponseDto.accountId());
        bankAccount.setType(accountResponseDto.accountType());
        bankAccount.setAccountState(accountResponseDto.accountState());
        bankAccount.setBalance(accountResponseDto.balance());
        bankAccount.setCreatedAt(accountResponseDto.createdAt());
        if(accountResponseDto.accountType().equals("CURRENT")){
            bankAccount.setOverdraft(accountResponseDto.overdraft());
        }

        else if (accountResponseDto.accountType().equals("SAVING")) {
            bankAccount.setInterestRate(accountResponseDto.interestRate());
        }
        bankAccount.setCustomer(null);

        return bankAccount;
    }

    public static Customer mapFromCustomerResponseDto(CustomerResponseDto customerResponseDto) {
            return Customer.newBuilder()
                    .setCustomerId(customerResponseDto.customerDto().customerId())
                    .setFirstname(customerResponseDto.customerDto().firstname())
                    .setLastname(customerResponseDto.customerDto().lastname())
                    .setEmail(customerResponseDto.customerDto().email())
                    .setCustomerState(customerResponseDto.customerDto().customerState())
                    .setCreatedAt(customerResponseDto.customerDto().createdAt())
                    .build();
    }

    public static OperationEntity mapFromOperation(Operation operation) {
            return OperationEntity.builder()
                    .operationId(operation.getOperationId())
                    .operationType(operation.getOperationType())
                    .transactionAmount(operation.getTransactionAmount())
                    .createdAt(operation.getCreatedAt())
                    .description(operation.getDescription())
                    .accountId(operation.getBankAccount().getAccountId())
                    .build();
    }

    public static OperationResponseDto mapToOperationResponseDto(OperationEntity operationEntity, AccountResponseDto accountResponseDto) {
            return new OperationResponseDto(
                    operationEntity.getOperationId(), operationEntity.getOperationType(), operationEntity.getTransactionAmount(),
                    operationEntity.getDescription(), operationEntity.getCreatedAt(),accountResponseDto
            );
    }
}
