package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topic {
    @Value("${kafka.topic-config.name}")
    private String topicName;
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
    public NewTopic createTopic(){
        return TopicBuilder.name(topicName)
                .partitions(topicPartitions)
                .replicas(topicReplicas)
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                .config(TopicConfig.RETENTION_BYTES_CONFIG, retentionSize)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,cleanUpPolicy)
                .build();
    }
}