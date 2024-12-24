package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.KafkaEventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase.InputServiceImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.feign_client.services.RemoteClientService;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class ConfigurationsSet {
    private static final Logger LOGGER = Logger.getLogger(ConfigurationsSet.class.getName());
    @Bean
    public InputServiceImpl configInputService(OutputService outputService, RemoteClientService remoteClientService,
                                               KafkaEventProducer kafkaEventProducer) {
        LOGGER.log(Level.INFO,"configure use case to be injected as spring bean");
        return new InputServiceImpl(outputService, remoteClientService, kafkaEventProducer);
    }

    @Value("${kafka.topic-config.name}")
    private String accountTopic;
    @Value("${kafka.topic-config.retention.duration}")
    private String retentionDuration;
    @Value("${kafka.topic-config.retention.size}")
    private String retentionSize;
    @Value("${kafka.topic-config.retention.clean-up-policy}")
    private String cleanUpPolicy;
    @Value("${kafka.topic-config.topic-partitions}")
    private int topicPartitions;
    @Value("${kafka.topic-config.topic-replicas}")
    private int topicReplicas;

    @Bean
    public NewTopic bankAccountTopic(){
        LOGGER.log(Level.INFO,"creating bank account topic and configuration");
        return TopicBuilder
                .name(accountTopic)
                .partitions(topicPartitions)
                .replicas(topicReplicas)
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                .config(TopicConfig.RETENTION_BYTES_CONFIG, retentionSize)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,cleanUpPolicy)
                .build();
    }

}
