package com.exalt.exalthexagonalarchikafkakeycloaknotificationapi.service;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendCustomerNotificationEmail(CustomerEvent customerEvent) throws MessagingException;
    void sendAccountNotificationEmail(BankAccountEvent bankAccountEvent) throws MessagingException;
    void sendActivateAccountNotificationEmail(ActiveAccountEvent activeAccountEvent) throws MessagingException;

    void sendOperationNotificationEmail(OperationEvent operationEvent) throws MessagingException;
    void sendTransferNotificationEmail(TransferEvent transferEvent) throws MessagingException;
}
