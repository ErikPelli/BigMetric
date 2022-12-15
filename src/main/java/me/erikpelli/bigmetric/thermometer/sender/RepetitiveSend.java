package me.erikpelli.bigmetric.thermometer.sender;

import me.erikpelli.bigmetric.thermometer.components.ReadCelsiusTemperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@EnableScheduling
@ConditionalOnProperty(prefix = "client.producer", name="enabled", havingValue="true", matchIfMissing = true)
class RepetitiveSend {
    private final SensorInitializer sensors;
    private final ReadCelsiusTemperature temperatureReader;
    private final KafkaSender sender;

    @Autowired
    RepetitiveSend(SensorInitializer sensors, ReadCelsiusTemperature temperatureReader, KafkaSender sender) {
        this.sensors = sensors;
        this.temperatureReader = temperatureReader;
        this.sender = sender;
    }

    /**
     * Send the current temperature every 10 seconds (10.000ms).
     */
    @Async
    @Scheduled(fixedRate = 10_000)
    void sendingService() {
        for (Sensor sensor : sensors.getSensors()) {
            var sensorId = sensor.sensorId();
            var temperature = temperatureReader.actualTemperature();
            var data = new KafkaData(sensorId, temperature);
            sender.sendTemperature(data);
        }
    }
}
