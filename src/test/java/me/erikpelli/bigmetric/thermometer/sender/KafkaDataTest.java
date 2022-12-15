package me.erikpelli.bigmetric.thermometer.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class KafkaDataTest {
    private KafkaData data;

    @BeforeEach
    void setUp() {
        data = new KafkaData(888, 1234, 50.0, "12345678");
    }

    @Test
    void version() {
        assertEquals(888, data.version());
        var withoutVersion = new KafkaData(1234, 50.0, "12345678");
        assertEquals(KafkaData.dataVersion, withoutVersion.version());
    }

    @Test
    void deviceId() {
        assertEquals(1234, data.deviceId());
    }

    @Test
    void temperature() {
        assertEquals(50.0, data.temperature());
    }

    @Test
    void createdAt() {
        assertEquals("12345678", data.createdAt());
        var automaticDate = new KafkaData(1234, 50.0);
        var regenerateDate = Instant.parse(automaticDate.createdAt()).toString();
        assertEquals(automaticDate.createdAt(), regenerateDate);
    }
}