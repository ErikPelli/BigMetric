package me.erikpelli.bigmetric.temperatureHandler.persistence;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Primary key for the Temperature Cassandra table.
 * CQL equivalent: PRIMARY KEY((sensorId), measureTime)
 * The partition is determined from the sensor id and the data
 * are saved in descending order (most recent first) using the
 * measureTime.
 */
@PrimaryKeyClass
public class TemperatureKey implements Serializable {
    @PrimaryKeyColumn(
            name = "sensorId",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED
    )
    private long sensorId;

    @PrimaryKeyColumn(
            name = "measureTime",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING
    )
    private Instant measureTime;

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public Instant getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(Instant measureTime) {
        this.measureTime = measureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemperatureKey that = (TemperatureKey) o;
        return sensorId == that.sensorId && measureTime.equals(that.measureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, measureTime);
    }
}
