package me.erikpelli.bigmetric.thermometer.measure;

import me.erikpelli.bigmetric.thermometer.components.TemperatureGetter;

/**
 * TemperatureGenerator represents a collected temperature that doesn't
 * come from a physical thermometer but from this Java application.
 * It implements the TemperatureGetter interface to have a common interface
 * for superior layers if in the future we decide to collect the temperature
 * in other ways.
 */
abstract class TemperatureGenerator implements TemperatureGetter {
    public abstract double getTemperature();

    @Override
    public String toString() {
        return "GeneratedTemp{" + getTemperature() + "}";
    }
}
