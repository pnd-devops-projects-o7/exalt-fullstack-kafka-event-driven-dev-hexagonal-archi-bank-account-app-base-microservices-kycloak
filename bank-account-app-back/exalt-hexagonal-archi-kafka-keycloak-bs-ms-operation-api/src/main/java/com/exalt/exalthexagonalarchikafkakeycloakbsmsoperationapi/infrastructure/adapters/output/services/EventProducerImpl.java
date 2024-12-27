package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventProducerImpl implements EventProducer {
    private final KafkaTemplate<String,Object> objectEventKafkaTemplate;
    @Value("${kafka.topic-config.topic1}")
    private String operationTopic;
    @Value("${kafka.topic-config.topic2}")
    private String transferTopic;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducerImpl.class.getName());
    @Override
    public void createOperationEvent(OperationEvent operationEvent) {
        LOGGER.debug("send operation event {} to kafka", operationEvent);
        Message<?> message = buildObjectMsg(operationEvent, operationTopic);
        objectEventKafkaTemplate.send(message);
    }

    @Override
    public void createTransfer(TransferEvent transferEvent) {
        LOGGER.debug("send transfer event {} to kafka", transferEvent);
        Message<?> message = buildObjectMsg(transferEvent, transferTopic);
        objectEventKafkaTemplate.send(message);
    }

    private Message<?> buildObjectMsg(Object objectEvent, String topic){
        return MessageBuilder
                .withPayload(objectEvent)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .setHeader(KafkaHeaders.PARTITION, 0)
                .build();
    }
}
