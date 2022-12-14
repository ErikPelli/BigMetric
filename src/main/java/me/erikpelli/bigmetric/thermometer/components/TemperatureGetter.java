package me.erikpelli.bigmetric.thermometer.components;

/**
 * An interface that returns the actual temperature when requested.
 */
public interface TemperatureGetter {
    /**
     * Return the actual double value for the temperature.
     * @return temperature in degrees.
     */
    double getTemperature();
}
