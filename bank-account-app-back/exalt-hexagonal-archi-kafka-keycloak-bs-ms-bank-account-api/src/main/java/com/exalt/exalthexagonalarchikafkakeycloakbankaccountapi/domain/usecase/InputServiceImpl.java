package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.AccountNotFoundException;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.BankAccountApiBusinessException;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.services.RemoteClientService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputServiceImpl implements InputService {
    private final OutputService outputService;
    private final RemoteClientService remoteClientService;
    private final EventProducer eventProducer;
    private static final Logger LOGGER = Logger.getLogger(InputServiceImpl.class.getName());
    private static final String SUSPENDED = "SUSPENDED";

    public InputServiceImpl(final OutputService outputService, final RemoteClientService remoteClientService, final EventProducer eventProducer) {
        this.outputService = outputService;
        this.remoteClientService = remoteClientService;
        this.eventProducer = eventProducer;
    }

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
        //checking input data validity
        if (AccountValidation.accountFieldsEmpty(accountRequestDto)) {
            LOGGER.log(Level.INFO, "check emptiness of payload fields {0}", accountRequestDto);
            throw new BankAccountApiBusinessException("one or many fields are empty");
        }
        if (!AccountValidation.validFirstDeposit(accountRequestDto.balance())) {
            throw new BankAccountApiBusinessException("to create account the first deposit balance is not enough");
        }

        if (!AccountValidation.isValidAccountType(accountRequestDto.type())) {
            throw new BankAccountApiBusinessException("account type provided invalid");
        }

        CustomerResponseDto customerResponseDto = remoteClientService.getRemoteCustomerById(accountRequestDto.customerId());
        if (AccountValidation.remoteClientApiUnreachable(customerResponseDto)) {
            throw new BankAccountApiBusinessException("remote customer client unreachable");
        }

        if (AccountValidation.remoteCustomerStateUnAllowed(customerResponseDto)) {
            throw new BankAccountApiBusinessException("remote customer state not allowed");
        }
        BankAccount bankAccount = MapperService.mapFromAccountRequestDto(accountRequestDto);
        bankAccount.setCustomer(MapperService.mapToCustomerFromCustomerResponseDto(customerResponseDto));
        LOGGER.log(Level.INFO, "build kafka event from dto {0}", accountRequestDto);

        BankAccountEvent bankAccountEvent = new BankAccountEvent();
        bankAccountEvent.setBankAccount(bankAccount);
        bankAccountEvent.setMessage("Bank Account CREATE event");
        bankAccountEvent.setStatus("CREATE ACCOUNT");

        BankAccountEntity bankAccountEntity = MapperService.mapToBankAccountEntity(bankAccount);

        if (bankAccountEntity instanceof CurrentAccount currentAccount) {
            LOGGER.log(Level.INFO, "call output connector to persist current account {0}", currentAccount);
            outputService.createCurrentAccount(currentAccount);
            LOGGER.log(Level.INFO, "call kafka connector to sent event into kafka infra");
            bankAccountEvent.setAccountType("CURRENT-ACCOUNT");
            eventProducer.createAccountEvent(bankAccountEvent);
        } else if (bankAccountEntity instanceof SavingAccount savingAccount) {
            LOGGER.log(Level.INFO, "call output connector to persist saving account {0}", savingAccount);
            outputService.createSavingAccount(savingAccount);
            LOGGER.log(Level.INFO, "call kafka connector to sent event into kafka infra");
            bankAccountEvent.setAccountType("SAVING-ACCOUNT");
            eventProducer.createAccountEvent(bankAccountEvent);
        }

        LOGGER.log(Level.INFO, "building bank account response dto");
        return MapperService.mapToAccountResponseDto(bankAccountEntity, customerResponseDto);
    }

    @Override
    public Collection<AccountResponseDto> getAllBankAccounts() {
        Collection<BankAccountEntity> bankAccountEntities = outputService.getAllBankAccounts();
        return bankAccountEntities.stream()
                .map(bankAccountEntity -> {
                    AccountResponseDto accountResponseDto;
                    CustomerResponseDto customerResponseDto = remoteClientService.getRemoteCustomerById(bankAccountEntity.getCustomerId());
                    if (bankAccountEntity instanceof CurrentAccount currentAccount) {
                        accountResponseDto = MapperService.mapToAccountResponseDto(currentAccount, customerResponseDto);
                        return accountResponseDto;
                    } else if (bankAccountEntity instanceof SavingAccount savingAccount) {
                        accountResponseDto = MapperService.mapToAccountResponseDto(savingAccount, customerResponseDto);
                        return accountResponseDto;
                    }
                    return null;
                }).toList();
    }

    @Override
    public AccountResponseDto getAccountById(String accountId) {
        BankAccountEntity bankAccountEntity = outputService.getAccountById(accountId);
        if (bankAccountEntity == null) {
            return null;
        }
        CustomerResponseDto customerResponseDto = remoteClientService.getRemoteCustomerById(bankAccountEntity.getCustomerId());
        return MapperService.mapToAccountResponseDto(bankAccountEntity, customerResponseDto);
    }


    @Override
    public AccountResponseDto suspendAccount(String accountId) {
        BankAccountEntity bankAccountEntity = outputService.getAccountById(accountId);
        if (bankAccountEntity == null) {
            throw new AccountNotFoundException(String.format("account with id %s not exist", accountId));
        }
        if (bankAccountEntity.getAccountState().equals(SUSPENDED)) {
            throw new BankAccountApiBusinessException("account already SUSPENDED");
        }
        bankAccountEntity.setAccountState(SUSPENDED);
        BankAccountEvent bankAccountEvent = new BankAccountEvent();
        //call output connector to save account with new state
        switch (bankAccountEntity) {
            case CurrentAccount currentAccount -> {
                outputService.createCurrentAccount(currentAccount);
                bankAccountEvent.setAccountType("CURRENT ACCOUNT");
            }
            case SavingAccount savingAccount -> {
                outputService.createSavingAccount(savingAccount);
                bankAccountEvent.setAccountType("SAVING ACCOUNT");
            }
            default -> {/*do nothing*/}
        }
        //build kafka event
        bankAccountEvent.setMessage("account is activated, ready for operations");
        bankAccountEvent.setStatus("SUSPENDED ACCOUNT");
        BankAccount bankAccount = MapperService.mapFromAccountEntity(bankAccountEntity);
        CustomerResponseDto customerResponseDto = remoteClientService.getRemoteCustomerById(bankAccountEntity.getCustomerId());
        bankAccount.setCustomer(MapperService.mapFromCustomerResponseDto(customerResponseDto));
        bankAccountEvent.setBankAccount(bankAccount);
        //call kafka connector to send kafka event into kafka infra
        eventProducer.suspendAccountEvent(bankAccountEvent);
        return MapperService.mapToAccountResponseDto(bankAccountEntity, customerResponseDto);
    }

    @Override
    public AccountResponseDto updateAccountBalance(String accountId, BigDecimal amount) {
        BankAccountEntity bankAccountEntity = outputService.getAccountById(accountId);
        //check account
        if (bankAccountEntity == null) {
            throw new AccountNotFoundException(String.format("account with id %s not found", accountId));
        }
        BankAccountEvent bankAccountEvent = new BankAccountEvent();
        bankAccountEntity.setBalance(bankAccountEntity.getBalance().add(amount));
        //call output connector to persist account
        switch (bankAccountEntity) {
            case CurrentAccount currentAccount -> {
                outputService.createCurrentAccount(currentAccount);
                bankAccountEvent.setAccountType("CURRENT ACCOUNT");
            }
            case SavingAccount savingAccount -> {
                outputService.createSavingAccount(savingAccount);
                bankAccountEvent.setAccountType("SAVING ACCOUNT");
            }
            default -> LOGGER.info("do nothing");
        }
        //build kafka event
        bankAccountEvent.setMessage("account balance is updated");
        bankAccountEvent.setStatus("UPDATE BALANCE");
        BankAccount bankAccount = MapperService.mapFromAccountEntity(bankAccountEntity);
        CustomerResponseDto customerResponseDto = remoteClientService.getRemoteCustomerById(bankAccountEntity.getCustomerId());
        bankAccount.setCustomer(MapperService.mapFromCustomerResponseDto(customerResponseDto));
        bankAccountEvent.setBankAccount(bankAccount);
        //call kafka connector to send kafka event into kafka infra
        eventProducer.updateAccountBalanceEvent(bankAccountEvent);
        return MapperService.mapToAccountResponseDto(bankAccountEntity, customerResponseDto);
    }

    @Override
    public AccountResponseDto updateAccountInterestRate(String accountId, Double interestRate) {
        BankAccountEntity bankAccountEntity = outputService.getAccountById(accountId);
        if (bankAccountEntity == null) {
            LOGGER.log(Level.INFO, "account not found");
            throw new AccountNotFoundException(String.format("account with id %s not found", accountId));
        }
        if (bankAccountEntity instanceof CurrentAccount) {
            LOGGER.log(Level.INFO, "invalid account");
            throw new BankAccountApiBusinessException("invalid account");
        }
        SavingAccount savingAccount = (SavingAccount) bankAccountEntity;
        savingAccount.setInterestRate(interestRate);
        //call output connector to persist account
        outputService.createSavingAccount(savingAccount);
        //build kafka event
        BankAccountEvent bankAccountEvent = new BankAccountEvent();
        bankAccountEvent.setAccountType("SAVING ACCOUNT");
        bankAccountEvent.setStatus("UPDATE IRATE");
        bankAccountEvent.setMessage("update interest rate");
        BankAccount bankAccount = MapperService.mapFromAccountEntity(savingAccount);
        CustomerResponseDto customerResponseDto = remoteClientService.getRemoteCustomerById(savingAccount.getCustomerId());
        bankAccount.setCustomer(MapperService.mapFromCustomerResponseDto(customerResponseDto));
        bankAccountEvent.setBankAccount(bankAccount);
        //call output connector to send event in kafka infra
        eventProducer.updateAccountInterestRateEvent(bankAccountEvent);
        return MapperService.mapToAccountResponseDto(savingAccount, customerResponseDto);
    }

    @Override
    public AccountResponseDto updateAccountOverdraft(String accountId, Double overdraft) {
        BankAccountEntity bankAccountEntity = outputService.getAccountById(accountId);
        if (bankAccountEntity == null) {
            throw new AccountNotFoundException(String.format("account with id %s not found", accountId));
        }
        if (bankAccountEntity instanceof SavingAccount) {
            LOGGER.log(Level.INFO, "invalid account");
            throw new BankAccountApiBusinessException("invalid account");
        }
        CurrentAccount currentAccount = (CurrentAccount) bankAccountEntity;
        currentAccount.setOverdraft(overdraft);
        //call output connector to persist account
        outputService.createCurrentAccount(currentAccount);
        //build kafka event
        BankAccountEvent bankAccountEvent = new BankAccountEvent();
        bankAccountEvent.setAccountType("CURRENT ACCOUNT");
        bankAccountEvent.setStatus("UPDATE OVERDRAFT");
        bankAccountEvent.setMessage("update account overdraft");
        BankAccount bankAccount = MapperService.mapFromAccountEntity(currentAccount);
        CustomerResponseDto customerResponseDto = remoteClientService.getRemoteCustomerById(currentAccount.getCustomerId());
        bankAccount.setCustomer(MapperService.mapFromCustomerResponseDto(customerResponseDto));
        bankAccountEvent.setBankAccount(bankAccount);
        //call output connector to send event in kafka infra
        eventProducer.updateAccountOverdraftEvent(bankAccountEvent);
        return MapperService.mapToAccountResponseDto(currentAccount, customerResponseDto);
    }
}
