package me.erikpelli.bigmetric.temperatureHandler.persistence;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access the Temperature table, with the support for an insert with TTL.
 */
@Repository
@ConditionalOnProperty(prefix = "cassandra.test", name = "enabled", havingValue = "true", matchIfMissing = true)
public interface TemperatureRepository extends CassandraRepository<Temperature, TemperatureKey>, CassandraCustomTTL<Temperature> {
}