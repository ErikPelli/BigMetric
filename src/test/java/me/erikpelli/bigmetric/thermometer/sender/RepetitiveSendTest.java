package me.erikpelli.bigmetric.thermometer.sender;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;

@SpringBootTest
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
                        Mockito.times(1)).sendingService()
                );
    }
}