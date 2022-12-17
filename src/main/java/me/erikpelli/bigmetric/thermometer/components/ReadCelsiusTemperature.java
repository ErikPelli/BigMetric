package me.erikpelli.bigmetric.thermometer.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Get a celsius degree temperature using Spring dependency injection.
 */
@Component
public class ReadCelsiusTemperature {
    private TemperatureGetter currentTempGetter;

    /**
     * Link the temperature getter to the ActualTemperature class.
     */
    @Autowired
    public void setTemperatureGetter(ActualTemperature t) {
        this.currentTempGetter = t;
    }

    /**
     * Permit the user to set a custom TemperatureGetter for the values
     * (useful in tests).
     *
     * @param t TemperatureGetter object
     */
    public ReadCelsiusTemperature(TemperatureGetter t) {
        this.currentTempGetter = t;
    }

    /**
     * Return the actual temperature when needed.
     *
     * @return Celsius degrees temperatures as double value
     */
    public double actualTemperature() {
        return currentTempGetter.getTemperature();
    }

    @Override
    public String toString() {
        return "ReadTemperature{" + actualTemperature() + " *C}";
    }
}
