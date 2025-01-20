package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.exceptions.OperationApiBusinessException;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.TransferInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.TransferOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteAccountService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteCustomerService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.TransferEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Logger;

public class TransferInputServiceImpl implements TransferInputService {
    private final RemoteAccountService remoteAccountService;
    private final RemoteCustomerService remoteCustomerService;
    private final TransferOutputService transferOutputService;
    private final EventProducer eventProducer;
    private static final String SAVING = "SAVING";
    private static final String CURRENT = "CURRENT";

    public TransferInputServiceImpl(final RemoteAccountService remoteAccountService, final RemoteCustomerService remoteCustomerService,
                                    final TransferOutputService transferOutputService, final EventProducer eventProducer) {
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
        AccountResponseDto originAccountResponseDto = remoteAccountService
                .getRemoteAccountById(transferRequestDto.originAccount());
        AccountResponseDto destinationAccountResponseDto = remoteAccountService
                .getRemoteAccountById(transferRequestDto.destinationAccount());

        checkRemoteAccountValidity(originAccountResponseDto, destinationAccountResponseDto, transferRequestDto);
        // check if transfer is between current saving account, only authorized for customer
        // that transfer many from its current towards saving account and vice versa
        CustomerResponseDto originCustomerResponseDto = remoteCustomerService.getRemoteCustomerById(
                originAccountResponseDto.customerResponseDto().customerDto().customerId());
        CustomerResponseDto destinationCustomerResponseDto = remoteCustomerService.getRemoteCustomerById(
                destinationAccountResponseDto.customerResponseDto().customerDto().customerId());

        // this following condition avoid transfer between current and saving account and vice versa for two different customers
        if ((originAccountResponseDto.accountType().equals(SAVING) && destinationAccountResponseDto.accountType().equals(CURRENT)
                || originAccountResponseDto.accountType().equals(CURRENT) && destinationAccountResponseDto.accountType().equals(SAVING)) &&
                (!originCustomerResponseDto.customerDto().customerId().equals(destinationCustomerResponseDto.customerDto().customerId()))) {
            throw new OperationApiBusinessException("this kind of transfer is only possible between current and saving account of a customer");
        }
        //debit origin account
        BigDecimal minusAmount = transferRequestDto.transferAmount().negate();
        remoteAccountService.updateBalanceCurrentAccount(originAccountResponseDto.accountId(), minusAmount);
        //credit destination account
        remoteAccountService.updateBalanceCurrentAccount(destinationAccountResponseDto.accountId(), transferRequestDto.transferAmount());

        //build transfer event
        Transfer transfer = TransferMapperService.partialMapFromRequestDto(transferRequestDto);
        OriginAccount originAccount = TransferMapperService
                .mapFromAccountResponseDto1(originAccountResponseDto, originCustomerResponseDto);
        DestinationAccount destinationAccount = TransferMapperService
                .mapFromAccountResponseDto2(destinationAccountResponseDto, destinationCustomerResponseDto);
        transfer.setOriginAccount(originAccount);
        transfer.setDestinationAccount(destinationAccount);
        //call output connector to persist transfer entity in db
        TransferEntity transferEntity = TransferMapperService.mapFromTransfer(transfer);
        transferOutputService.createTransfer(transferEntity);
        //call output connector to send transfer event to kafka topic
        TransferEvent transferEvent = TransferMapperService.mapToTransferEvent(transfer);
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

    @Override
    public TransferResponseDto getTransfer(String transferId) {
        TransferEntity transferEntity = transferOutputService.getTransfer(transferId);
        if(transferEntity!=null){
            AccountResponseDto origin = remoteAccountService.getRemoteAccountById(transferEntity.getOriginAccountId());
            AccountResponseDto destination = remoteAccountService.getRemoteAccountById(transferEntity.getDestinationAccountId());

            return mapToTransferResponseDTo(transferEntity,origin,destination);
        }
        return null;
    }

    //this private method builds user readable response
    private TransferResponseDto mapToTransferResponseDTo(TransferEntity transferEntity, AccountResponseDto origin, AccountResponseDto destination) {
        return new TransferResponseDto(transferEntity.getTransferId(), transferEntity.getTransferAmount(),
                transferEntity.getDescription(), transferEntity.getCreatedAt(), origin, destination);
    }

    //check validity
    private void checkRemoteAccountValidity(AccountResponseDto originAccountResponseDto, AccountResponseDto destinationAccountResponseDto,
                                            TransferRequestDto transferRequestDto) {
        final Logger logger = Logger.getLogger(TransferMapperService.class.getSimpleName());
        if (ValidatorTools.remoteAccountClientUnreachable(
                originAccountResponseDto) || ValidatorTools.remoteAccountClientUnreachable(destinationAccountResponseDto)) {
            logger.info("log remote origin or destination account unreachable");
            throw new OperationApiBusinessException("remote origin or destination account unreachable");
        }
        //check remote accounts state allowed for transfer
        if (ValidatorTools.remoteAccountStateUnAllowed(originAccountResponseDto.accountState())
                || ValidatorTools.remoteAccountStateUnAllowed(destinationAccountResponseDto.accountState())) {
            logger.info("log remote origin or destination account unreachable");
            throw new OperationApiBusinessException("remote origin or destination account state not allowed");
        }
        //check origin account balance enough
        if (ValidatorTools.accountBalanceInsufficient(originAccountResponseDto, transferRequestDto.transferAmount())) {
            logger.info("log remote account balance insufficient");
            throw new OperationApiBusinessException("remote account balance insufficient");
        }

        // check if transfer is between same account
        if (originAccountResponseDto.accountId().equals(destinationAccountResponseDto.accountId())) {
            logger.info("what are you doing!!!!!!");
            throw new OperationApiBusinessException("what are you doing!!!!!!");
        }
    }
}
