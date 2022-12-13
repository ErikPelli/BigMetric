package me.erikpelli.bigmetric.thermometer.measure;

import me.erikpelli.bigmetric.thermometer.measure.RandomTemp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomTempTest {
    RandomTemp generator;

    @BeforeEach
    void setUp() {
        generator = new RandomTemp();
    }

    @Test
    void setBound() {
        generator.setBound(-10000, 10000);
        assertEquals(-10000, generator.getLowerBound());
        assertEquals(10000, generator.getUpperBound());

        generator.setBound(10, 10);
        assertEquals(10, generator.getTemperature());

        assertThrows(IllegalArgumentException.class, () -> {
            generator.setBound(1, -1);
        });
    }

    @Test
    void getLowerBound() {
        assertNull(generator.getLowerBound());
        generator.setBound(10, 11);
        assertEquals(10, generator.getLowerBound());
    }

    @Test
    void getUpperBound() {
        assertNull(generator.getUpperBound());
        generator.setBound(10, 11);
        assertEquals(11, generator.getUpperBound());
    }

    @Test
    void getTemperature() {
        assertDoesNotThrow(() -> {
            generator.getTemperature();
        });
    }
}