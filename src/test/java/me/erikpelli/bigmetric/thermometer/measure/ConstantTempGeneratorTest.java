package me.erikpelli.bigmetric.thermometer.measure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantTempGeneratorTest {

    @Test
    void getTemperature() {
        int temp = 0;
        var generator = new ConstantTempGenerator(temp);
        assertEquals(temp, generator.getTemperature());
    }
}