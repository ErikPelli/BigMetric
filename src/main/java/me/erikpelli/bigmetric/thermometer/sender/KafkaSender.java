package me.erikpelli.bigmetric.thermometer.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    private final KafkaTemplate<String, KafkaData> kafkaTemplate;
    private final String temperatureTopic;

    @Autowired
    KafkaSender(KafkaTemplate<String, KafkaData> kafkaTemplate, @Value("${kafka.topicName}") String temperatureTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.temperatureTopic = temperatureTopic;
    }

    /**
     * Send a generic message using kafka.
     *
     * @param topicName kafka topic to use
     * @param data      data to send
     */
    void sendMessage(String topicName, KafkaData data) {
        kafkaTemplate.send(topicName, data);
    }

    /**
     * Send temperature data using the preconfigured Kafka topic.
     *
     * @param data temperature data to send
     */
    public void sendTemperature(KafkaData data) {
        sendMessage(this.temperatureTopic, data);
    }
}
