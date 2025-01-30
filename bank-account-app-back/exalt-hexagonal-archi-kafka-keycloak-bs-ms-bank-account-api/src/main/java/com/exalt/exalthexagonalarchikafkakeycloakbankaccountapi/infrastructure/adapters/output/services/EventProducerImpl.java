package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EventProducerImpl implements EventProducer {
    private final KafkaTemplate<String, Object> accountEventKafkaTemplate;
    private static final Logger LOGGER = Logger.getLogger(EventProducerImpl.class.getName());
    @Value("${kafka.topic-config.topic1}")
    private String topic1;
    @Value("${kafka.topic-config.topic2}")
    private String topic2;

    public EventProducerImpl(final KafkaTemplate<String, Object> accountEventKafkaTemplate) {
        this.accountEventKafkaTemplate = accountEventKafkaTemplate;
    }

    @Override
    public void createAccountEvent(BankAccountEvent bankAccountEvent) {
        LOGGER.log(Level.INFO,"send built create message {0} into kafka infra",bankAccountEvent);
        accountEventKafkaTemplate.send(topic1,"0",bankAccountEvent);
    }

    @Override
    public void activateAccountCronJobEvent(ActiveAccountEvent activeAccountEvent) {
        LOGGER.log(Level.INFO,"send build activate message {0} into kafka infra", activeAccountEvent);
        accountEventKafkaTemplate.send(topic2,0, "0",activeAccountEvent);
    }

    @Override
    public void switchAccountStateEvent(BankAccountEvent bankAccountEvent) {
        LOGGER.log(Level.INFO,"send build suspend message {0} into kafka infra", bankAccountEvent);
        accountEventKafkaTemplate.send(topic1,1,"1",bankAccountEvent);
    }

    @Override
    public void updateAccountBalanceEvent(BankAccountEvent bankAccountEvent) {
        LOGGER.log(Level.INFO,"send update balance message {0} into kafka infra", bankAccountEvent);
        accountEventKafkaTemplate.send(topic1,2,"2",bankAccountEvent);
    }

    @Override
    public void updateAccountOverdraftOrInterestRateEvent(BankAccountEvent bankAccountEvent) {
        LOGGER.log(Level.INFO,"send update interest rate message {0} into kafka infra", bankAccountEvent);
        accountEventKafkaTemplate.send(topic1,3,"3",bankAccountEvent);
    }
}
