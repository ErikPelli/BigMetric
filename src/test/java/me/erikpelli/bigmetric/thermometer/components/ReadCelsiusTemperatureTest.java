package me.erikpelli.bigmetric.thermometer.components;

import me.erikpelli.bigmetric.thermometer.measure.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"client.producer.min-temp=1", "client.producer.max-temp=1"})
class ReadCelsiusTemperatureTest {

    @Autowired
    private ReadCelsiusTemperature readCelsiusTemperature;

    @Test
    void actualTemperatureExternalGetter() {
        int expected = 10;
        var constantTemperature = new ConstantTempGenerator(expected);
        var temperatureReader = new ReadCelsiusTemperature(constantTemperature);
        assertEquals(expected, (int) temperatureReader.actualTemperature());
    }

    @Test
    void actualTemperature() {
        assertEquals(1.0, readCelsiusTemperature.actualTemperature());
    }
}