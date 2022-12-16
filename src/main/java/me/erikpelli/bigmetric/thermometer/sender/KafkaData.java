package me.erikpelli.bigmetric.thermometer.sender;

import java.time.Instant;

/**
 * Object that contains the information to be sent to the Kafka consumers.
 *
 * @param version     current version of data, can be incremented in the future
 * @param deviceId    identifier of the current temperature sensor
 * @param temperature floating point value of the measured temperature
 * @param createdAt   ISO8601 date that represents the moment when the measure has been done
 */
public record KafkaData(int version, int deviceId, double temperature, String createdAt) {
    final static int dataVersion = 1;

    KafkaData(int deviceId, double temperature, String createdAt) {
        this(dataVersion, deviceId, temperature, createdAt);
    }

    KafkaData(int deviceId, double temperature) {
        this(dataVersion, deviceId, temperature, getCurrentISO8601());
    }

    private static String getCurrentISO8601() {
        return Instant.now().toString();
    }
}
