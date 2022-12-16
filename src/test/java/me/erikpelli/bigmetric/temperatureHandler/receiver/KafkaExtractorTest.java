package me.erikpelli.bigmetric.temperatureHandler.receiver;

import me.erikpelli.bigmetric.thermometer.sender.KafkaData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KafkaExtractorTest {
    private final KafkaExtractor kafkaExtractor = new KafkaExtractor();
    private final KafkaData kafkaData = new KafkaData(1, 1234, 50.0, "2022-12-16T20:56:56Z");

    @Test
    void getDeviceId() {
        assertEquals(kafkaData.deviceId(), kafkaExtractor.getDeviceId(kafkaData));
    }

    @Test
    void getTemperatureValue() {
        assertEquals(kafkaData.temperature(), kafkaExtractor.getTemperatureValue(kafkaData));
    }

    @Test
    void getTime() {
        assertEquals(kafkaData.createdAt(), kafkaExtractor.getTime(kafkaData).toString());
    }
}