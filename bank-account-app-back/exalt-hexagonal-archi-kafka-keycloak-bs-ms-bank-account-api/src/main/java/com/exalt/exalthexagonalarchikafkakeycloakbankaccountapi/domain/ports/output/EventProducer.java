package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccountEvent;

public interface EventProducer {
    void createAccountEvent(BankAccountEvent bankAccountEvent);
    void activateAccountEvent(ActiveAccountEvent activeAccountEvent);

    void suspendAccountEvent(BankAccountEvent bankAccountEvent);

    void updateAccountBalanceEvent(BankAccountEvent bankAccountEvent);

    void updateAccountInterestRateEvent(BankAccountEvent bankAccountEvent);

    void updateAccountOverdraftEvent(BankAccountEvent bankAccountEvent);
}
