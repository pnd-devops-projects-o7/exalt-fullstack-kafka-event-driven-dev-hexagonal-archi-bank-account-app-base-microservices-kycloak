package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {
    @Value("${kafka.topic-config.topic1}")
    private String topicName1;
    @Value("${kafka.topic-config.topic2}")
    private String topicName2;
    @Value("${kafka.topic-config.partitions}")
    private int partitions;
    @Value("${kafka.topic-config.retention.duration}")
    private String retentionDuration;
    @Value("${kafka.topic-config.retention.size}")
    private String retentionSize;
    @Value("${kafka.topic-config.retention.clean-up-policy}")
    private String cleanUpPolicy;

    @Bean
    public NewTopic operationAccountTopic(){
        return TopicBuilder.name(topicName1)
                .partitions(partitions)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                .config(TopicConfig.RETENTION_BYTES_CONFIG, retentionSize)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,cleanUpPolicy)
                .build();
    }
    @Bean
    public NewTopic activeAccountTopic(){
        return TopicBuilder.name(topicName2)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionDuration)
                .config(TopicConfig.RETENTION_BYTES_CONFIG, retentionSize)
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,cleanUpPolicy)
                .build();
    }
}