package me.erikpelli.bigmetric.thermometer.components;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActualTemperatureTest {

    @Autowired
    private ActualTemperature generatorBound;

    @Value("${client.producer.min-temp}")
    int minTemperature;

    @Value("${client.producer.max-temp}")
    int maxTemperature;

    @Value("${client.test.temperature-checks}")
    private int cycleIterations;

    @Test
    void getTemperature() {
        for (var i = 0; i < cycleIterations; i++) {
            var result = generatorBound.getTemperature();
            assertTrue(result >= minTemperature && result < maxTemperature, "Temperature out of range");
        }
    }
}