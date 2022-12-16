package me.erikpelli.bigmetric.temperatureHandler.receiver;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class for KafkaData consumer.
 */
@Configuration
class ConsumerTopicConfig {
    @Bean
    public NewTopic consumerTemperatureTopic(@Value("${kafka.topicName}") String temperatureTopic) {
        return TopicBuilder.name(temperatureTopic).build();
    }
}
