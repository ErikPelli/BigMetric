package me.erikpelli.bigmetric.thermometer.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * SensorLocation contains the possible locations for the sensors
 * and a redefined circular iterator to iterate continuously over them.
 */
enum SensorLocation implements Iterable<SensorLocation> {
    ASIA,
    AFRICA,
    EUROPE,
    NORTH_AMERICA,
    SOUTH_AMERICA,
    OCEANIA,
    ANTARCTICA;

    @Override
    @NonNull
    public Iterator<SensorLocation> iterator() {
        return new SensorLocationIterator();
    }

    class SensorLocationIterator implements Iterator<SensorLocation> {
        SensorLocation next = SensorLocation.this;

        @Override
        public SensorLocation next() {
            try {
                return next;
            } finally {
                next = values()[(next.ordinal() + 1) % values().length];
            }
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}


/**
 * Sensor contains the data for a single sensor.
 *
 * @param location actual location of the sensor
 * @param sensorId actual identifier of the sensor
 */
record Sensor(SensorLocation location, int sensorId) {
}

/**
 * SensorInitializer initializes a list with the information of the sensors
 * to impersonate in this client java program.
 */
@Component
class SensorInitializer {
    private final List<Sensor> sensors;

    /**
     * SensorInitializer constructor called by Spring framework.
     */
    SensorInitializer(@Value("${client.producer.sensor-id-start}") int firstId,
                      @Value("${client.producer.num-sensors}") int numSensors) {
        this.sensors = generateSensorsData(firstId, numSensors);
    }

    /**
     * Generate data about the sensors and return a list of them.
     *
     * @param firstId    first unique identifier to be incremented for each sensor
     * @param numSensors total number of sensors to initialize
     * @return List of sensors
     */
    private List<Sensor> generateSensorsData(int firstId, int numSensors) {
        var sensorArrayList = new ArrayList<Sensor>(numSensors);
        var locationIterator = SensorLocation.ASIA.iterator();

        for (var i = firstId; i < firstId + numSensors; i++) {
            sensorArrayList.add(new Sensor(locationIterator.next(), i));
        }
        return sensorArrayList;
    }

    /**
     * Get the actual list of sensors.
     *
     * @return List of sensors
     */
    List<Sensor> getSensors() {
        return sensors;
    }
}