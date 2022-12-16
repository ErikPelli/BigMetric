package me.erikpelli.bigmetric.temperatureHandler.receiver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"cassandra.test.enabled=false"})
@EnableAutoConfiguration(exclude = {CassandraAutoConfiguration.class})
class TemperatureFilterTest {
    @Value("${client.consumer.min-temp}")
    private int minTemp;

    @Value("${client.consumer.max-temp}")
    private int maxTemp;

    @Autowired
    private TemperatureFilter temperatureFilter;

    @Test
    void isTemperatureInRange() {
        assertFalse(temperatureFilter.isTemperatureInRange(minTemp-1));
        assertTrue(temperatureFilter.isTemperatureInRange(minTemp));
        assertTrue(temperatureFilter.isTemperatureInRange(maxTemp));
        assertFalse(temperatureFilter.isTemperatureInRange(maxTemp+1));
    }
}