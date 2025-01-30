package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input.ScheduledJob;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.BankAccountEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.CurrentAccount;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity.SavingAccount;

import java.util.Collection;

public class ScheduledJobImpl implements ScheduledJob {
    private final OutputService outputService;
    private final EventProducer eventProducer;

    public ScheduledJobImpl(final OutputService outputService, final EventProducer eventProducer) {
        this.outputService = outputService;
        this.eventProducer = eventProducer;
    }

    @Override
    public void activateAccountSchedule() {
        Collection<BankAccountEntity> createdAccounts = outputService.getAllAccountsByStateCreated();
        createdAccounts.forEach(bankAccountEntity -> {
            bankAccountEntity.setAccountState("ACTIVE");
            ActiveAccountEvent activeAccountEvent = new ActiveAccountEvent();
            activeAccountEvent.setStatus("ACCOUNT ACTIVATED");
            ActiveAccount activeAccount = new ActiveAccount();
            //call connector to persist account with new state
            if (bankAccountEntity instanceof CurrentAccount currentAccount) {
                outputService.createCurrentAccount(currentAccount);
                activeAccountEvent.setMessage("Current Account is activated");
                activeAccount.setType("CURRENT ACCOUNT");
                activeAccount.setOverdraft(currentAccount.getOverdraft());
                activeAccount.setInterestRate(0);
            } else if (bankAccountEntity instanceof SavingAccount savingAccount) {
                outputService.createSavingAccount(savingAccount);
                activeAccountEvent.setMessage("Saving Account is activated");
                activeAccount.setType("SAVING ACCOUNT");
                activeAccount.setInterestRate(savingAccount.getInterestRate());
                activeAccount.setOverdraft(0);
            }
            //continue building active account event for kafka topic
            activeAccount.setAccountId(bankAccountEntity.getAccountId());
            activeAccount.setAccountState(bankAccountEntity.getAccountState());
            activeAccount.setCreatedAt(bankAccountEntity.getCreatedAt());
            activeAccount.setBalance(bankAccountEntity.getBalance());
            activeAccountEvent.setActiveAccount(activeAccount);
            //call kafka connector to send kafka event into kafka infra
            eventProducer.activateAccountCronJobEvent(activeAccountEvent);
        });
    }
}
