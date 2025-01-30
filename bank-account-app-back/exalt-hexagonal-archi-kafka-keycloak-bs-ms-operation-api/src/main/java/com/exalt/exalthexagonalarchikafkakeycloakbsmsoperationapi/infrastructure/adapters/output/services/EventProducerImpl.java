package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EventProducerImpl implements EventProducer {
    private final KafkaTemplate<String,Object> objectEventKafkaTemplate;
    @Value("${kafka.topic-config.topic1}")
    private String operationTopic;
    @Value("${kafka.topic-config.topic2}")
    private String transferTopic;
    private static final Logger log = LoggerFactory.getLogger(EventProducerImpl.class.getName());

    public EventProducerImpl(final KafkaTemplate<String, Object> objectEventKafkaTemplate) {
        this.objectEventKafkaTemplate = objectEventKafkaTemplate;
    }

    @Override
    public void createOperationEvent(OperationEvent operationEvent) {
        log.debug("send operation event {} to kafka", operationEvent);
        objectEventKafkaTemplate.send(operationTopic,0,"0",operationEvent);
    }

    @Override
    public void createTransfer(TransferEvent transferEvent) {
        log.debug("send transfer event {} to kafka", transferEvent);
        objectEventKafkaTemplate.send(transferTopic,0,"0",transferEvent);
    }
}
