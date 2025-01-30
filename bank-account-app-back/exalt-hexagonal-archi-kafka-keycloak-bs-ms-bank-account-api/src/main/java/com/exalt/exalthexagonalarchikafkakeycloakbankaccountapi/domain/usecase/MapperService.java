package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.Customer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;

import java.time.Instant;
import java.util.UUID;

public class MapperService {
    private static final Float INIT_INTEREST_RATE = 2.5f;
    private static final Double INIT_OVERDRAFT = 10.0;
    private static final String CURRENT_ACCOUNT = "CURRENT";
    private static final String SAVING_ACCOUNT = "SAVING";

    private MapperService() {
    }

    public static BankAccount mapFromAccountRequestDto(AccountRequestDto accountRequestDto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountId(UUID.randomUUID().toString());
        bankAccount.setAccountState("CREATED");
        bankAccount.setType(accountRequestDto.type());
        bankAccount.setBalance(accountRequestDto.balance());
        bankAccount.setCreatedAt(Instant.now());
        bankAccount.setCustomer(null);
        if (accountRequestDto.type().equals(CURRENT_ACCOUNT)) {
            bankAccount.setOverdraft(INIT_OVERDRAFT);
        } else if (accountRequestDto.type().equals(SAVING_ACCOUNT)) {
            bankAccount.setInterestRate(INIT_INTEREST_RATE);
        }
        return bankAccount;
    }

    public static BankAccountEntity mapToBankAccountEntity(BankAccount bankAccount) {
        if (bankAccount.getType().equals(CURRENT_ACCOUNT)) {
            CurrentAccount currentAccount = new CurrentAccount();
            currentAccount.setAccountId(bankAccount.getAccountId());
            currentAccount.setAccountState(bankAccount.getAccountState());
            currentAccount.setBalance(bankAccount.getBalance());
            currentAccount.setCreatedAt(bankAccount.getCreatedAt());
            currentAccount.setCustomerId(bankAccount.getCustomer().getCustomerId());
            currentAccount.setOverdraft(INIT_OVERDRAFT);
            return currentAccount;
        } else if (bankAccount.getType().equals(SAVING_ACCOUNT)) {
            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setAccountId(bankAccount.getAccountId());
            savingAccount.setAccountState(bankAccount.getAccountState());
            savingAccount.setBalance(bankAccount.getBalance());
            savingAccount.setCreatedAt(bankAccount.getCreatedAt());
            savingAccount.setCustomerId(bankAccount.getCustomer().getCustomerId());
            savingAccount.setInterestRate(INIT_INTEREST_RATE);
            return savingAccount;
        }

        return null;
    }

    public static AccountResponseDto mapToAccountResponseDto(BankAccountEntity bankAccountEntity, CustomerResponseDto customerResponseDto) {

        if (bankAccountEntity instanceof CurrentAccount currentAccount) {
            return new AccountResponseDto(
                    currentAccount.getAccountId(),
                    CURRENT_ACCOUNT,
                    currentAccount.getAccountState(),
                    currentAccount.getBalance(),
                    currentAccount.getOverdraft(),
                    null,
                    currentAccount.getCreatedAt(),
                    customerResponseDto);
        } else if (bankAccountEntity instanceof SavingAccount savingAccount) {
            return new AccountResponseDto(
                    savingAccount.getAccountId(),
                    SAVING_ACCOUNT,
                    savingAccount.getAccountState(),
                    savingAccount.getBalance(),
                    null,
                    savingAccount.getInterestRate(),
                    savingAccount.getCreatedAt(),
                    customerResponseDto);
        }
        return null;
    }

    public static Customer mapToCustomerFromCustomerResponseDto(CustomerResponseDto customerResponseDto) {
        return Customer.newBuilder()
                .setCustomerId(customerResponseDto.customerDto().customerId())
                .setFirstname(customerResponseDto.customerDto().firstname())
                .setLastname(customerResponseDto.customerDto().lastname())
                .setEmail(customerResponseDto.customerDto().email())
                .setCustomerState(customerResponseDto.customerDto().customerState())
                .setCreatedAt(customerResponseDto.customerDto().createdAt())
                .build();
    }

    public static BankAccount mapFromAccountEntity(BankAccountEntity bankAccountEntity) {
        BankAccount bankAccount = new BankAccount();
        if (bankAccountEntity instanceof CurrentAccount currentAccount) {
            bankAccount.setType(CURRENT_ACCOUNT);
            bankAccount.setOverdraft(currentAccount.getOverdraft());
        } else if (bankAccountEntity instanceof SavingAccount savingAccount) {
            bankAccount.setType(SAVING_ACCOUNT);
            bankAccount.setInterestRate(savingAccount.getInterestRate());
        }
        bankAccount.setAccountId(bankAccountEntity.getAccountId());
        bankAccount.setAccountState(bankAccountEntity.getAccountState());
        bankAccount.setBalance(bankAccountEntity.getBalance());
        bankAccount.setCreatedAt(bankAccountEntity.getCreatedAt());
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
}
