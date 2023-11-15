package com.ezze.movies.domain.outbound;

import com.ezze.movies.domain.exceptions.MetricsRepositoryException;
import com.ezze.movies.domain.model.Metrics;

/**
 * MetricsRepository enables the creation, retrieving and deletion metrics
 */
public interface MetricsRepository {

    /**
     * Enables the creation of a new metrics in the persistence layer
     * @param metrics metrics to create
     * @return ID of the created metrics
     * @throws MetricsRepositoryException if there was a persistence-related error
     */
    String create(Metrics metrics) throws MetricsRepositoryException;

    /**
     * Enables the retrieving of a metrics by a given ID
     * @param id the ID to search
     * @return the found metrics
     * @throws MetricsRepositoryException if there was a persistence-related error
     */
    Metrics getById(String id) throws MetricsRepositoryException;

    /**
     * Enables the deletion of a given metrics
     * @param metrics metrics to delete
     * @throws MetricsRepositoryException if there was a persistence-related error
     */
    void delete(Metrics metrics) throws MetricsRepositoryException;
}
