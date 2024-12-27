package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EventProducerImpl implements EventProducer {
    private final KafkaTemplate<String, BankAccountEvent> kafkaTemplate;
    private static final Logger LOGGER = Logger.getLogger(EventProducerImpl.class.getName());
    @Value("${kafka.topic-config.name}")
    private String topic;
    @Override
    public void createAccountEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,0);
        LOGGER.log(Level.INFO,"send built create message {0} into kafka infra",message);
        kafkaTemplate.send(message);
    }

    @Override
    public void activateAccountEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,1);
        LOGGER.log(Level.INFO,"send build activate message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    @Override
    public void suspendAccountEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,2);
        LOGGER.log(Level.INFO,"send build suspend message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    @Override
    public void updateAccountBalanceEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,3);
        LOGGER.log(Level.INFO,"send update balance message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    @Override
    public void updateAccountInterestRateEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,4);
        LOGGER.log(Level.INFO,"send update interest rate message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    @Override
    public void updateAccountOverdraftEvent(BankAccountEvent bankAccountEvent) {
        Message<?> message = messageBuild(bankAccountEvent,5);
        LOGGER.log(Level.INFO,"send update overdraft message {0} into kafka infra", message);
        kafkaTemplate.send(message);
    }

    private Message<?> messageBuild(final BankAccountEvent bankAccountEvent, int partition){
        LOGGER.log(Level.INFO,"building kafka message with model {0}",bankAccountEvent);
        return MessageBuilder
                .withPayload(bankAccountEvent)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .setHeader(KafkaHeaders.PARTITION,partition)
                .build();
    }

}
