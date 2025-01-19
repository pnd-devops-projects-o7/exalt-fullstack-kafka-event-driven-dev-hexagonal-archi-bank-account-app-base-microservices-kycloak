package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.service;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.EventProducer;
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
public class EventProducerImpl implements EventProducer {
    private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;
    @Value("${kafka.topic-config.name}")
    private String topic;
    private static final Logger LOGGER = Logger.getLogger(EventProducerImpl.class.getName());

    public EventProducerImpl(final KafkaTemplate<String, CustomerEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createCustomerEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event: {0} to create customer",customerEvent);
        kafkaTemplate.send(buildKafkaMessage(customerEvent,0));
    }

    @Override
    public void updateCustomerEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event: {0} to update customer",customerEvent);
        kafkaTemplate.send(buildKafkaMessage(customerEvent,1));
    }

    @Override
    public void customerArchiveEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event {0} to archive customer", customerEvent);
        kafkaTemplate.send(buildKafkaMessage(customerEvent,2));
    }

    @Override
    public void customerSuspendEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event {0} to suspend customer", customerEvent);
        kafkaTemplate.send(buildKafkaMessage(customerEvent,3));
    }

    private Message<?> buildKafkaMessage(CustomerEvent customerEvent, int partition){
        return MessageBuilder
                .withPayload(customerEvent)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.PARTITION,partition)
                .build();
    }
}
