package me.erikpelli.bigmetric.temperatureHandler.persistence;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Temperature Cassandra Table used to save temperatures in Cassandra DB.
 * Table Fields:
 * - sensor_id (bigint)
 * - measure_time (timestamp)
 * - temperature_value (double)
 */
@Table
public class Temperature {
    @PrimaryKey
    private TemperatureKey idAndTime;

    @Column(value = "temperature_value")
    private double value;

    public TemperatureKey getCompositePrimaryKey() {
        return idAndTime;
    }

    public void setCompositePrimaryKey(TemperatureKey primaryKey) {
        this.idAndTime = primaryKey;
    }

    public double getTemperature() {
        return value;
    }

    public void setTemperature(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Temperature{" + "idAndTime=" + idAndTime + ", Value=" + value + '}';
    }
}