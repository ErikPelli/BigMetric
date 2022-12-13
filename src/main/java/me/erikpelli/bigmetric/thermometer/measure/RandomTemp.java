package me.erikpelli.bigmetric.thermometer.measure;

import java.util.concurrent.ThreadLocalRandom;

public class RandomTemp extends TemperatureGenerator {
    private Integer lower, upper;

    /**
     * Random generator without a defined bound.
     */
    public RandomTemp() {
    }

    /**
     * Random generator with a minimum and maximum output value.
     *
     * @param lower minimum value of temperature
     * @param upper maximum value of temperature
     */
    public RandomTemp(int lower, int upper) {
        setBound(lower, upper);
    }

    /**
     * Set a new temperature bound for the current generator.
     *
     * @param lower minimum value of temperature
     * @param upper maximum value of temperature
     */
    public void setBound(int lower, int upper) {
        if (upper < lower) {
            throw new IllegalArgumentException("bound range is invalid");
        }
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Get the current lower temperature bound.
     *
     * @return integer lower bound
     */
    public Integer getLowerBound() {
        return lower;
    }

    /**
     * Get the current upper temperature bound.
     *
     * @return integer upper bound
     */
    public Integer getUpperBound() {
        return upper;
    }

    /**
     * Generate a random double number.
     *
     * @return random-generated number
     */
    private double generateNumber() {
        return ThreadLocalRandom.current().nextDouble();
    }

    /**
     * Generate a random double number.
     *
     * @param min inclusive minimum value
     * @param max exclusive maximum value
     * @return random-generated number
     */
    private double generateNumber(int min, int max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * Generate a random temperature for test purposes.
     *
     * @return Current random double number
     */
    public double getTemperature() {
        if (lower == null || upper == null) {
            // Undefined bound
            return generateNumber();
        }
        if (lower.intValue() == upper.intValue()) {
            // Constant value
            return lower.doubleValue();
        }
        return generateNumber(lower, upper);
    }
}
