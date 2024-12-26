package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.BankAccountEvent;

public interface EventProducer {
    void createAccountEvent(BankAccountEvent bankAccountEvent);
    void activateAccountEvent(BankAccountEvent bankAccountEvent);

    void suspendAccountEvent(BankAccountEvent bankAccountEvent);

    void updateAccountBalance(BankAccountEvent bankAccountEvent);
}
