package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.service;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.CustomerProducerEvent;
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
public class CustomerProducerEventImpl implements CustomerProducerEvent {
    private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;
    @Value("${kafka.topic-config.name}")
    private String topic;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void createCustomerEvent(CustomerEvent customerEvent) {
        logger.log(Level.INFO,"placing customer event: {0} to create customer",customerEvent);
        kafkaTemplate.send(buildKafkaMessage(customerEvent,0));
    }

    @Override
    public void updateCustomerEvent(CustomerEvent customerEvent) {
        logger.log(Level.INFO,"placing customer event: {0} to update customer",customerEvent);
        kafkaTemplate.send(buildKafkaMessage(customerEvent,1));
    }

    @Override
    public void customerArchiveEvent(CustomerEvent customerEvent) {
        logger.log(Level.INFO,"placing customer event {0} to archive customer", customerEvent);
        kafkaTemplate.send(buildKafkaMessage(customerEvent,2));
    }

    private Message<?> buildKafkaMessage(CustomerEvent customerEvent, int partition){
        return MessageBuilder
                .withPayload(customerEvent)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.PARTITION,partition)
                .build();
    }
}
