package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.config.kafka;

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
    private String topicBankAccount;
    @Value("${kafka.topic-config.topic2}")
    private String topicBankAccountActivated;
    @Value("${kafka.topic-config.retention.duration}")
    private String retentionDuration;
    @Value("${spring.kafka.bootstrap-servers}")
    private String [] bootstrapServers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Arrays.asList(bootstrapServers));
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic bankAccountTopic() {
        NewTopic newTopic = new NewTopic(topicBankAccount, 4, (short) 1);
        newTopic.configs(configureTopics());
        return newTopic;
    }
    @Bean
    public NewTopic bankAccountActivatedTopic() {
       NewTopic newTopic = new NewTopic(topicBankAccountActivated, 1, (short) 1);
       newTopic.configs(configureTopics());
       return newTopic;
    }

    private Map<String, String> configureTopics() {
        Map<String, String> configs = new HashMap<>();
        configs.put(TopicConfig.RETENTION_MS_CONFIG,retentionDuration);
        return configs;
    }
}