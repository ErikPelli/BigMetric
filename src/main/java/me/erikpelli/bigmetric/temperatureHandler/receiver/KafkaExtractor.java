package me.erikpelli.bigmetric.temperatureHandler.receiver;

import me.erikpelli.bigmetric.thermometer.sender.KafkaData;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Class to extract information from the received KafkaData object.
 */
@Component
class KafkaExtractor {
    /**
     * Get the device id.
     *
     * @param received Object to get the data from
     * @return integer id
     */
    int getDeviceId(KafkaData received) {
        return received.deviceId();
    }

    /**
     * Get the temperature.
     *
     * @param received Object to get the data from
     * @return double temperature
     */
    double getTemperatureValue(KafkaData received) {
        return received.temperature();
    }

    /**
     * Get the measurement time instant.
     *
     * @param received Object to get the data from
     * @return java Instant object
     */
    Instant getTime(KafkaData received) {
        return Instant.parse(received.createdAt());
    }
}
