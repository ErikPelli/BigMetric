package me.erikpelli.bigmetric.thermometer.sender;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SensorInitializerTest {
    @Autowired
    private SensorInitializer sensorInitializer;

    @Value("${client.producer.num-sensors}")
    private int sensorsNum;

    @Value("${client.producer.sensor-id-start}")
    private int firstId;

    @Test
    void getSensors() {
        var sensors = sensorInitializer.getSensors();
        assertEquals(sensorsNum, sensors.size());

        var validLocations = SensorLocation.values();

        for (Sensor sensor : sensors) {
            var isEnumValid = false;
            for (SensorLocation loc : validLocations) {
                if (loc == sensor.location()) {
                    isEnumValid = true;
                    break;
                }
            }
            assertTrue(isEnumValid);
            assertTrue(sensor.sensorId() >= firstId);
        }
    }
}