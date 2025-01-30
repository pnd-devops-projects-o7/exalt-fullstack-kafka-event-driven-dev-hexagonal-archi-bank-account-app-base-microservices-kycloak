package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.service;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.EventProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EventProducerImpl implements EventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${kafka.topic-config.name}")
    private String topic;
    private static final Logger LOGGER = Logger.getLogger(EventProducerImpl.class.getName());

    public EventProducerImpl(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createCustomerEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event: {0} to create customer",customerEvent);
        kafkaTemplate.send(topic,0,"0",customerEvent);
    }

    @Override
    public void updateCustomerEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event: {0} to update customer",customerEvent);
        kafkaTemplate.send(topic,1,"1",customerEvent);
    }

    @Override
    public void customerArchiveEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event {0} to archive customer", customerEvent);
        kafkaTemplate.send(topic,2,"2",customerEvent);
    }

    @Override
    public void customerSuspendEvent(CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO,"placing customer event {0} to suspend customer", customerEvent);
        kafkaTemplate.send(topic,3,"3",customerEvent);
    }
}
