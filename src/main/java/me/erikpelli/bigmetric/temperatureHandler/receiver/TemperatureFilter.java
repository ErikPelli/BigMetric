package me.erikpelli.bigmetric.temperatureHandler.receiver;

import me.erikpelli.bigmetric.temperatureHandler.persistence.Temperature;
import me.erikpelli.bigmetric.temperatureHandler.persistence.TemperatureKey;
import me.erikpelli.bigmetric.temperatureHandler.persistence.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
class TemperatureFilter {
    private final int minTemp;
    private final int maxTemp;
    private final int ttl;
    private TemperatureRepository temperatureRepository;

    @Autowired(required = false)
    public void setTemperatureRepository(TemperatureRepository t) {
        this.temperatureRepository = t;
    }

    TemperatureFilter(@Value("${client.consumer.min-temp}") int minTemp,
                      @Value("${client.consumer.max-temp}") int maxTemp,
                      @Value("${client.consumer.temperature-ttl}") int ttl) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.ttl = ttl;
    }

    /**
     * Return true if the temperature is in the valid range.
     * @param temperature temperature to check
     * @return boolean validity
     */
    protected boolean isTemperatureInRange(double temperature) {
        return temperature >= minTemp && temperature <= maxTemp;
    }

    /**
     * Save the received temperature data inside the Cassandra DB.
     * @param deviceId identifier of the sensor
     * @param temperature measured value of the temperature
     * @param createdAt instant when the value has been measured (UTC)
     */
    protected void saveTemperature(int deviceId, double temperature, Instant createdAt) {
        if (!isTemperatureInRange(temperature)) {
            return;
        }

        var key = new TemperatureKey();
        key.setSensorId(deviceId);
        key.setMeasureTime(createdAt);

        var measure = new Temperature();
        measure.setCompositePrimaryKey(key);
        measure.setTemperature(temperature);

        if (temperatureRepository != null) {
            temperatureRepository.save(measure, ttl);
        }
    }
}
