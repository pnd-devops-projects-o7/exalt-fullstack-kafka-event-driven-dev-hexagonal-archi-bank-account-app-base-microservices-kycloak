package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {
    @Value("${kafka.topic-config.topic1}")
    private String operationTopic;
    @Value("${kafka.topic-config.topic2}")
    private String transferTopic;
    @Value("${kafka.topic-config.retention-config.duration}")
    private String retentionDuration;
    @Value("${kafka.topic-config.retention-config.size}")
    private String retentionSize;
    @Value("${kafka.topic-config.retention-config.clean-up-policy}")
    private String cleanUpPolicy;
    @Bean
    public NewTopic createOperationTopic(){
        return TopicBuilder
                .name(operationTopic)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                .config(TopicConfig.RETENTION_BYTES_CONFIG, retentionSize)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,cleanUpPolicy)
                .build();
    }

    @Bean
    public NewTopic createTransferTopic(){
        return TopicBuilder
                .name(transferTopic)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                .config(TopicConfig.RETENTION_BYTES_CONFIG, retentionSize)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,cleanUpPolicy)
                .build();
    }
}
