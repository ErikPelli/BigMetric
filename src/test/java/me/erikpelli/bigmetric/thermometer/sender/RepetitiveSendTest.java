package me.erikpelli.bigmetric.thermometer.sender;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;

@SpringBootTest(properties = {"client.producer.enabled=true", "cassandra.test.enabled=false"})
@EnableAutoConfiguration(exclude = {CassandraAutoConfiguration.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class RepetitiveSendTest {
    @SpyBean
    private RepetitiveSend repetitiveSend;

    @Test
    void sendingService() {
        Awaitility.await()
                .atMost(Duration.ofSeconds(15))
                .untilAsserted(() -> Mockito.verify(
                        repetitiveSend,
                        Mockito.times(2)).sendingService()
                );
    }
}

@SpringBootTest(properties = {"client.producer.enabled=false", "cassandra.test.enabled=false"})
@EnableAutoConfiguration(exclude = {CassandraAutoConfiguration.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class DisabledRepetitiveSendTest {
    @SpyBean
    private RepetitiveSend repetitiveSend;

    @Test
    void sendingService() {
        Awaitility.await()
                .during(Duration.ofSeconds(15))
                .atMost(Duration.ofSeconds(20))
                .untilAsserted(() -> Mockito.verify(
                        repetitiveSend,
                        Mockito.never()).sendingService()
                );
    }
}