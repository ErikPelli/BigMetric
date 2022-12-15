package me.erikpelli.bigmetric.thermometer.sender;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class for KafkaData producer.
 */
@Configuration
class ProducerTopicConfig {
    @Bean
    public NewTopic producerTemperatureTopic(@Value("${kafka.topicName}") String temperatureTopic) {
        return TopicBuilder.name(temperatureTopic).build();
    }
}
