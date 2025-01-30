package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic-config.topic1}")
    private String operationTopic;
    @Value("${kafka.topic-config.topic2}")
    private String transferTopic;
    @Value("${kafka.topic-config.retention-config.duration}")
    private String retentionDuration;

    @Value("${spring.kafka.bootstrap-servers}")
    private String [] bootstrapServers;

    @Bean
    public KafkaAdmin KafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Arrays.asList(bootstrapServers));
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic operationTopic() {
        NewTopic newTopic = new NewTopic(operationTopic, 1, (short) 1);
        newTopic.configs(configureTopic());
        return newTopic;
    }

    @Bean
    public NewTopic transferTopic() {
        NewTopic newTopic = new NewTopic(transferTopic, 1, (short) 1);
        newTopic.configs(configureTopic());
        return newTopic;
    }

    private Map<String, String >configureTopic() {
        Map<String, String > topicConfigs = new HashMap<>();
        topicConfigs.put(TopicConfig.RETENTION_MS_CONFIG, retentionDuration);
        return topicConfigs;
    }
}
