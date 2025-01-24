package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopics {
    @Value("${kafka.topic-config.name}")
    private String topicName;
    @Value("${kafka.topic-config.retention.duration}")
    private String retentionDuration;

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(topicName)
                .partitions(4)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,"compact,delete")
                .build();
    }
}