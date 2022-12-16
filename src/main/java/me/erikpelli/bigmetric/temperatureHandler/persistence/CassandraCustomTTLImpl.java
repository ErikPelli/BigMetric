package me.erikpelli.bigmetric.temperatureHandler.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.stereotype.Component;

/**
 * Effective implementation of the save function with a TTL, automatically imported by Spring.
 *
 * @param <T> Cassandra Table Class
 */
@Component
@ConditionalOnProperty(prefix = "cassandra.test", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CassandraCustomTTLImpl<T> implements CassandraCustomTTL<T> {
    private final CassandraOperations operations;

    @Autowired
    public CassandraCustomTTLImpl(CassandraOperations operations) {
        this.operations = operations;
    }

    @Override
    public <S extends T> S save(S entity, int ttl) {
        InsertOptions insertOptions = InsertOptions.builder().ttl(ttl).build();
        operations.insert(entity, insertOptions);
        return entity;
    }
}
