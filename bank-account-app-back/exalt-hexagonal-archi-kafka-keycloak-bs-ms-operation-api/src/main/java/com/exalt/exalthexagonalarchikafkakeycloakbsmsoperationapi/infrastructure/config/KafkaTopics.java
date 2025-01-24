package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.config;

import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopics {
    @Value("${kafka.topic-config.topic1}")
    private String operationTopic;
    @Value("${kafka.topic-config.topic2}")
    private String transferTopic;
    @Value("${kafka.topic-config.retention-config.duration}")
    private String retentionDuration;

    @Bean
    public KafkaAdmin.NewTopics newTopics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(operationTopic)
                        .partitions(1)
                        .replicas(1)
                        .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                        .config(TopicConfig.CLEANUP_POLICY_CONFIG, "compact, delete")
                        .build(),
                TopicBuilder.name(transferTopic)
                        .partitions(1)
                        .replicas(1)
                        .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                        .config(TopicConfig.CLEANUP_POLICY_CONFIG, "compact, delete")
                        .build()
        );
    }
}
