package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationCustomer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginCustomer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.exceptions.OperationApiBusinessException;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.TransferInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.TransferOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services.RemoteAccountService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services.RemoteCustomerService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.TransferEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Logger;

public class TransferInputServiceImpl implements TransferInputService {
    private final RemoteAccountService remoteAccountService;
    private final RemoteCustomerService remoteCustomerService;
    private final TransferOutputService transferOutputService;
    private final EventProducer eventProducer;
    private static final Logger LOGGER = Logger.getLogger(TransferInputServiceImpl.class.getSimpleName());
    private static final String SAVING = "SAVING";
    private static final String CURRENT = "CURRENT";

    public TransferInputServiceImpl(RemoteAccountService remoteAccountService, RemoteCustomerService remoteCustomerService,
                                    TransferOutputService transferOutputService, EventProducer eventProducer) {
        this.remoteAccountService = remoteAccountService;
        this.remoteCustomerService = remoteCustomerService;
        this.transferOutputService = transferOutputService;
        this.eventProducer = eventProducer;
    }

    @Override
    public TransferResponseDto createTransfer(TransferRequestDto transferRequestDto) {
        //check payload data validity
        if (ValidatorTools.transferRequestDtoFieldsEmpty(transferRequestDto)) {
            throw new OperationApiBusinessException("some input payload fields are invalid");
        }
        //check remote account api reachability
        AccountResponseDto originAccountResponseDto = remoteAccountService.getRemoteAccountById(transferRequestDto.originAccount());
        AccountResponseDto destinationAccountResponseDto = remoteAccountService.getRemoteAccountById(transferRequestDto.destinationAccount());
        if (ValidatorTools.remoteAccountClientUnreachable(
                originAccountResponseDto) || ValidatorTools.remoteAccountClientUnreachable(destinationAccountResponseDto)) {
            throw new OperationApiBusinessException("remote origin or destination account unreachable");
        }
        //check remote accounts state allowed for transfer
        if (ValidatorTools.remoteAccountStateUnAllowed(originAccountResponseDto.accountState())
                || ValidatorTools.remoteAccountStateUnAllowed(destinationAccountResponseDto.accountState())) {
            throw new OperationApiBusinessException("remote origin or destination account state not allowed");
        }
        //check origin account balance enough
        if (ValidatorTools.accountBalanceInsufficient(originAccountResponseDto.balance(), transferRequestDto.transferAmount())) {
            throw new OperationApiBusinessException("remote account balance insufficient");
        }

        // check if transfer is between same account
        if (originAccountResponseDto.accountId().equals(destinationAccountResponseDto.accountId())) {
            throw new OperationApiBusinessException("what are you doing!!!!!!");
        }

        // check if transfer is between current saving account, only authorized for customer
        // that transfer many from its current towards saving account and vice versa
        CustomerResponseDto originCustomerResponseDto = remoteCustomerService.getRemoteCustomerById(
                originAccountResponseDto.customerResponseDto().customerDto().customerId());
        CustomerResponseDto destinationCustomerResponseDto = remoteCustomerService.getRemoteCustomerById(
                destinationAccountResponseDto.customerResponseDto().customerDto().customerId());

        if ((originAccountResponseDto.accountType().equals(SAVING) && destinationAccountResponseDto.accountType().equals(CURRENT)
                || originAccountResponseDto.accountType().equals(CURRENT) && destinationAccountResponseDto.accountType().equals(SAVING)) &&
                (!originCustomerResponseDto.customerDto().customerId().equals(destinationCustomerResponseDto.customerDto().customerId()))) {
            throw new OperationApiBusinessException("this kind of transfer is only possible for same customer");
        }
        //debit origin account
        BigDecimal minusAmount = transferRequestDto.transferAmount().negate();
        remoteAccountService.updateAccountBalance(originAccountResponseDto.accountId(), minusAmount);
        //credit destination account
        remoteAccountService.updateAccountBalance(destinationAccountResponseDto.accountId(), transferRequestDto.transferAmount());

        //build transfer event
        Transfer transfer = new Transfer();
        transfer.setTransferId(UUID.randomUUID().toString());
        transfer.setTransferAmount(transferRequestDto.transferAmount());
        transfer.setDescription(transferRequestDto.description());
        transfer.setCreatedAt(Instant.now());

        OriginAccount originAccount = new OriginAccount();
        originAccount.setAccountId(originAccountResponseDto.accountId());
        originAccount.setType(originAccountResponseDto.accountType());
        originAccount.setAccountState(originAccountResponseDto.accountState());
        originAccount.setBalance(originAccountResponseDto.balance());
        originAccount.setCreatedAt(originAccountResponseDto.createdAt());
        originAccount.setOriginCustomer(OriginCustomer.newBuilder()
                .setCustomerId(originCustomerResponseDto.customerDto().customerId())
                .setFirstname(originCustomerResponseDto.customerDto().firstname())
                .setLastname(originCustomerResponseDto.customerDto().lastname())
                .setEmail(originCustomerResponseDto.customerDto().email())
                .setCustomerState(originCustomerResponseDto.customerDto().customerState())
                .setCreatedAt(originCustomerResponseDto.customerDto().createdAt())
                .build());

        DestinationAccount destinationAccount = new DestinationAccount();
        destinationAccount.setAccountId(destinationAccountResponseDto.accountId());
        destinationAccount.setType(destinationAccountResponseDto.accountType());
        destinationAccount.setAccountState(destinationAccountResponseDto.accountState());
        destinationAccount.setBalance(destinationAccountResponseDto.balance());
        destinationAccount.setCreatedAt(destinationAccountResponseDto.createdAt());
        destinationAccount.setDestinationCustomer(DestinationCustomer.newBuilder()
                .setCustomerId(destinationCustomerResponseDto.customerDto().customerId())
                .setFirstname(destinationCustomerResponseDto.customerDto().firstname())
                .setLastname(destinationCustomerResponseDto.customerDto().lastname())
                .setEmail(destinationCustomerResponseDto.customerDto().email())
                .setCustomerState(destinationCustomerResponseDto.customerDto().customerState())
                .setCreatedAt(destinationCustomerResponseDto.customerDto().createdAt())
                .build());

        switch (originAccountResponseDto.accountType()) {
            case CURRENT -> {
                originAccount.setOverdraft(originAccountResponseDto.overdraft());
                originAccount.setInterestRate(0);
            }
            case SAVING -> {
                originAccount.setOverdraft(0);
                originAccount.setInterestRate(originAccountResponseDto.interestRate());
            }
            default -> LOGGER.info("do nothing ");
        }
        switch (destinationAccountResponseDto.accountType()){
            case CURRENT -> {
                destinationAccount.setOverdraft(destinationAccountResponseDto.overdraft());
                destinationAccount.setInterestRate(0);
            }
            case SAVING -> {
                destinationAccount.setOverdraft(0);
                destinationAccount.setInterestRate(destinationAccountResponseDto.interestRate());
            }
            default -> LOGGER.info("do nothing");
        }
        transfer.setOriginAccount(originAccount);
        transfer.setDestinationAccount(destinationAccount);
        //call output connector to persist transfer entity in db
        TransferEntity transferEntity = TransferEntity.builder()
                .transferId(transfer.getTransferId())
                .originAccountId(transfer.getOriginAccount().getAccountId())
                .destinationAccountId(transfer.getDestinationAccount().getAccountId())
                .transferAmount(transfer.getTransferAmount())
                .description(transfer.getDescription())
                .createdAt(transfer.getCreatedAt())
                .build();
        transferOutputService.createTransfer(transferEntity);
        //call output connector to send transfer event to kafka topic
        TransferEvent transferEvent = TransferEvent.newBuilder()
                .setStatus("TRANSFER")
                .setMessage("Transfer operation")
                .setTransfer(transfer)
                .build();
        eventProducer.createTransfer(transferEvent);
        //build user readable response
        return mapToTransferResponseDTo(transferEntity, originAccountResponseDto, destinationAccountResponseDto);
    }

    @Override
    public Collection<TransferResponseDto> getAllTransfers() {
        Collection<TransferEntity> transferEntities = transferOutputService.getAllTransfers();
        return transferEntities.stream()
                .map(transferEntity -> {
                    AccountResponseDto origin = remoteAccountService.getRemoteAccountById(transferEntity.getOriginAccountId());
                    AccountResponseDto destination = remoteAccountService.getRemoteAccountById(transferEntity.getDestinationAccountId());
                    return mapToTransferResponseDTo(transferEntity, origin, destination);
                })
                .toList();
    }

    //this private method builds user readable response
    private TransferResponseDto mapToTransferResponseDTo(TransferEntity transferEntity, AccountResponseDto origin, AccountResponseDto destination) {
        return new TransferResponseDto(transferEntity.getTransferId(),transferEntity.getTransferAmount(),
                transferEntity.getDescription(), transferEntity.getCreatedAt(),origin, destination);
    }
}
