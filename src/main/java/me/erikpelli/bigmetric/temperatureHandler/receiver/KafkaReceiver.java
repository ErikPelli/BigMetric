package me.erikpelli.bigmetric.temperatureHandler.receiver;

import me.erikpelli.bigmetric.thermometer.sender.KafkaData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaReceiver {
    private final TemperatureFilter filter;
    private final KafkaExtractor dataExtract;
    private KafkaData latest;

    KafkaReceiver(TemperatureFilter filter, KafkaExtractor dataExtract) {
        this.filter = filter;
        this.dataExtract = dataExtract;
    }

    /**
     * Handle the received value from Kafka and save it.
     *
     * @param temperatureData Value saved in KafkaData object
     */
    @KafkaListener(topics = "${kafka.topicName}", groupId = "${spring.kafka.consumer.group-id}")
    public void temperatureListener(KafkaData temperatureData) {
        latest = temperatureData;
        filter.saveTemperature(
                dataExtract.getDeviceId(temperatureData),
                dataExtract.getTemperatureValue(temperatureData),
                dataExtract.getTime(temperatureData)
        );
    }

    /**
     * Get latest received data from the temperature topic.
     *
     * @return received data
     */
    public KafkaData getLatest() {
        return latest;
    }
}
