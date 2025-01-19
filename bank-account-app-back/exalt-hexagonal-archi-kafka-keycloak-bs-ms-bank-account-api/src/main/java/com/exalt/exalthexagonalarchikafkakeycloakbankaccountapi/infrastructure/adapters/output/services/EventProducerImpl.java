package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EventProducerImpl implements EventProducer {
    private final KafkaTemplate<String, BankAccountEvent> kafkaTemplate;
    private static final Logger LOGGER = Logger.getLogger(EventProducerImpl.class.getName());
    @Value("${kafka.topic-config.topic1}")
    private String topic1;
    @Value("${kafka.topic-config.topic2}")
    private String topic2;

    public EventProducerImpl(final KafkaTemplate<String, BankAccountEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createAccountEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,topic1,0);
        LOGGER.log(Level.INFO,"send built create message {0} into kafka infra",message);
        kafkaTemplate.send(message);
    }

    @Override
    public void activateAccountEvent(ActiveAccountEvent activeAccountEvent) {
        Message<?> message = messageBuild(activeAccountEvent,topic2,0);
        LOGGER.log(Level.INFO,"send build activate message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    @Override
    public void switchAccountStateEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,topic1,1);
        LOGGER.log(Level.INFO,"send build suspend message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    @Override
    public void updateAccountBalanceEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,topic1,2);
        LOGGER.log(Level.INFO,"send update balance message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    @Override
    public void updateAccountOverdraftOrInterestRateEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,topic1,3);
        LOGGER.log(Level.INFO,"send update interest rate message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    private Message<?> messageBuild(final Object bankAccountEvent, String topic, int partition){
        LOGGER.log(Level.INFO,"building kafka message with model {0}",bankAccountEvent);
        return MessageBuilder
                .withPayload(bankAccountEvent)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .setHeader(KafkaHeaders.PARTITION,partition)
                .build();
    }

}
