package me.erikpelli.bigmetric;

import me.erikpelli.bigmetric.temperatureHandler.receiver.KafkaReceiver;
import me.erikpelli.bigmetric.thermometer.sender.KafkaData;
import me.erikpelli.bigmetric.thermometer.sender.KafkaSender;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"cassandra.test.enabled=false"})
@EnableAutoConfiguration(exclude = {CassandraAutoConfiguration.class})
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class KafkaIntegrationTest {
    @Autowired
    private KafkaSender sender;

    @Autowired
    private KafkaReceiver consumer;

    @Test
    void contextLoads() {
    }

    @Test
    @Timeout(value = 15)
    void sendAndReceiveData() {
        var data = new KafkaData(1, 1234, 50.0, "2022-12-16T20:56:56Z");
        sender.sendTemperature(data);

        Awaitility.await()
                .atMost(Duration.ofSeconds(10))
                .until(() -> consumer.getLatest() != null);

        var receivedData = consumer.getLatest();
        assertEquals(data.version(), receivedData.version());
        assertEquals(data.deviceId(), receivedData.deviceId());
        assertEquals(data.temperature(), receivedData.temperature());
        assertEquals(data.createdAt(), receivedData.createdAt());
    }
}
