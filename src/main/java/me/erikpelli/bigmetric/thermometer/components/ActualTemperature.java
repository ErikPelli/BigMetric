package me.erikpelli.bigmetric.thermometer.components;

import me.erikpelli.bigmetric.thermometer.measure.RandomTemp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ActualTemperature wrap the RandomTemp class and add context from Spring framework.
 */
@Component
class ActualTemperature implements TemperatureGetter {
    private final TemperatureGetter tempGetter;

    /**
     * Get the actual random temperature with a minimum and maximum value.
     *
     * @param min Minimum temperature using spring configuration file
     * @param max Maximum temperature using spring configuration file
     */
    private ActualTemperature(@Value("${client.producer.min-temp}") int min,
                              @Value("${client.producer.max-temp}") int max) {
        this.tempGetter = new RandomTemp(min, max);
    }

    @Override
    public double getTemperature() {
        return tempGetter.getTemperature();
    }
}
