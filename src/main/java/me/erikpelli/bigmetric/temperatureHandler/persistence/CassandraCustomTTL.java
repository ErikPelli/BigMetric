package me.erikpelli.bigmetric.temperatureHandler.persistence;

/**
 * Add Time To Live support to Cassandra insertion.
 *
 * @param <T> Cassandra Table Class
 */
interface CassandraCustomTTL<T> {
    <S extends T> S save(S entity, int ttl);
}
