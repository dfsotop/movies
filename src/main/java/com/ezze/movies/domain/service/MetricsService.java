package com.ezze.movies.domain.service;

import com.ezze.movies.domain.exceptions.MetricsManagementException;
import com.ezze.movies.domain.model.Metrics;

/**
 * Enables the management of metrics
 */
public interface MetricsService {

    /**
     * Enables the creation of a metrics
     * @param metrics new metrics to create
     * @return ID of the created metrics
     * @throws MetricsManagementException if there was a persistence related error
     */
    String create(Metrics metrics) throws MetricsManagementException;

    Metrics getById(String id) throws MetricsManagementException;
    /**
     * Enables the deletion of a metrics
     * @param metrics
     * @throws MetricsManagementException if there was a persistence related error
     */
    void delete(Metrics metrics) throws MetricsManagementException;

}
