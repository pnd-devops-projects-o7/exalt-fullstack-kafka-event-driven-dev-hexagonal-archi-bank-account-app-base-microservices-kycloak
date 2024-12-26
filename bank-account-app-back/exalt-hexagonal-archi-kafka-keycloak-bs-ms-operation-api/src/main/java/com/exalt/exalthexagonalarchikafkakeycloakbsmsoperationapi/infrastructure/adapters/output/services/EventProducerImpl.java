package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
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
    private final KafkaTemplate<String,OperationEvent> operationEventKafkaTemplate;
    @Value("${kafka.topic-config.topic1}")
    private String operationTopic;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducerImpl.class.getName());
    @Override
    public void createOperationEvent(OperationEvent operationEvent) {
        LOGGER.debug("send kafka event");
        Message<?> message = buildOperationMsg(operationEvent);
        operationEventKafkaTemplate.send(message);
    }

    private Message<?> buildOperationMsg(Object operationEvent){
        return MessageBuilder
                .withPayload(operationEvent)
                .setHeader(KafkaHeaders.TOPIC,operationTopic)
                .setHeader(KafkaHeaders.PARTITION, 0)
                .build();
    }
}
