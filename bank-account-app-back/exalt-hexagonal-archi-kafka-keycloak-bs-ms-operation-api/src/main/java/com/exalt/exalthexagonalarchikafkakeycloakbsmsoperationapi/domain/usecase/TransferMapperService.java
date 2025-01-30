package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationCustomer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginCustomer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.TransferEntity;

import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

public class TransferMapperService {
    private TransferMapperService(){}
    private static final String CURRENT="CURRENT";
    private static final String SAVING="SAVING";
    private static final Logger LOGGER = Logger.getLogger(TransferMapperService.class.getSimpleName());
    public static OriginAccount mapFromAccountResponseDto1(AccountResponseDto accountResponseDto, CustomerResponseDto customerResponseDto){
        OriginAccount originAccount = new OriginAccount();
        originAccount.setAccountId(accountResponseDto.accountId());
        originAccount.setType(accountResponseDto.accountType());
        originAccount.setAccountState(accountResponseDto.accountState());
        originAccount.setBalance(accountResponseDto.balance());
        originAccount.setCreatedAt(accountResponseDto.createdAt());
        originAccount.setOriginCustomer(OriginCustomer.newBuilder()
                .setCustomerId(customerResponseDto.customerDto().customerId())
                .setFirstname(customerResponseDto.customerDto().firstname())
                .setLastname(customerResponseDto.customerDto().lastname())
                .setEmail(customerResponseDto.customerDto().email())
                .setCustomerState(customerResponseDto.customerDto().customerState())
                .setCreatedAt(customerResponseDto.customerDto().createdAt())
                .build());
        switch (accountResponseDto.accountType()) {
            case CURRENT -> {
                originAccount.setOverdraft(accountResponseDto.overdraft());
                originAccount.setInterestRate(0);
            }
            case SAVING -> {
                originAccount.setOverdraft(0);
                originAccount.setInterestRate(accountResponseDto.interestRate());
            }
            default -> LOGGER.info("do nothing");
        }
        return originAccount;
    }

    public static DestinationAccount mapFromAccountResponseDto2(AccountResponseDto accountResponseDto,
                                                                CustomerResponseDto customerResponseDto){
        DestinationAccount destinationAccount = new DestinationAccount();
        destinationAccount.setAccountId(accountResponseDto.accountId());
        destinationAccount.setType(accountResponseDto.accountType());
        destinationAccount.setAccountState(accountResponseDto.accountState());
        destinationAccount.setBalance(accountResponseDto.balance());
        destinationAccount.setCreatedAt(accountResponseDto.createdAt());
        destinationAccount.setDestinationCustomer(DestinationCustomer.newBuilder()
                .setCustomerId(customerResponseDto.customerDto().customerId())
                .setFirstname(customerResponseDto.customerDto().firstname())
                .setLastname(customerResponseDto.customerDto().lastname())
                .setEmail(customerResponseDto.customerDto().email())
                .setCustomerState(customerResponseDto.customerDto().customerState())
                .setCreatedAt(customerResponseDto.customerDto().createdAt())
                .build());
        switch (accountResponseDto.accountType()) {
            case CURRENT -> {
                destinationAccount.setOverdraft(accountResponseDto.overdraft());
                destinationAccount.setInterestRate(0);
            }
            case SAVING -> {
                destinationAccount.setOverdraft(0);
                destinationAccount.setInterestRate(accountResponseDto.interestRate());
            }
            default -> {/*do nothing here*/}
        }
        return destinationAccount;
    }

    public static TransferEntity mapFromTransfer(Transfer transfer){
        return TransferEntity.builder()
                .transferId(transfer.getTransferId())
                .originAccountId(transfer.getOriginAccount().getAccountId())
                .destinationAccountId(transfer.getDestinationAccount().getAccountId())
                .transferAmount(transfer.getTransferAmount())
                .description(transfer.getDescription())
                .createdAt(transfer.getCreatedAt())
                .build();
    }

    public static TransferEvent mapToTransferEvent(Transfer transfer){
        return TransferEvent.newBuilder()
                .setStatus("TRANSFER")
                .setMessage("Transfer operation")
                .setTransfer(transfer)
                .build();
    }

    public static Transfer partialMapFromRequestDto(TransferRequestDto transferRequestDto) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(UUID.randomUUID().toString());
        transfer.setTransferAmount(transferRequestDto.transferAmount());
        transfer.setDescription(transferRequestDto.description());
        transfer.setCreatedAt(Instant.now());

        return transfer;
    }
}
