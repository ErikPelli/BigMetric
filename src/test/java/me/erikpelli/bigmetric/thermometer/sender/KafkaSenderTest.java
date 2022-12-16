package me.erikpelli.bigmetric.thermometer.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"cassandra.test.enabled=false"})
@EnableAutoConfiguration(exclude = {CassandraAutoConfiguration.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class KafkaSenderTest {

    @Autowired
    private KafkaSender sender;

    @Test
    @Timeout(value = 10)
    void sendTemperature() {
        var data = new KafkaData(1, 1234, 50.0, "1234");
        assertDoesNotThrow(() -> {
            sender.sendTemperature(data);
        });
    }
}