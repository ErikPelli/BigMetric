package me.erikpelli.bigmetric.thermometer.measure;

/**
 * Return always the same degree value passed in the constructor.
 * This class is a deterministic specialization of TemperatureGenerator
 * and can be useful for test purposes.
 */
public class ConstantTempGenerator extends TemperatureGenerator {
    private final int degrees;

    public ConstantTempGenerator(int degrees) {
        this.degrees = degrees;
    }

    @Override
    public double getTemperature() {
        return degrees;
    }
}
