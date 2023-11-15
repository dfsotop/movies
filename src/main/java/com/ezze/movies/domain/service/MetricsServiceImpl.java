package com.ezze.movies.domain.service;

import com.ezze.movies.domain.exceptions.MetricsManagementException;
import com.ezze.movies.domain.exceptions.MetricsRepositoryException;
import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.domain.outbound.MetricsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;

    @Override
    public String create(Metrics metrics) throws MetricsManagementException {
        try {
            return metricsRepository.create(metrics);
        } catch (MetricsRepositoryException ex) {
            throw new MetricsManagementException(ex);
        }
    }

    @Override
    public Metrics getById(String id) throws MetricsManagementException {
        try {
            return metricsRepository.getById(id);
        } catch (MetricsRepositoryException ex) {
            throw new MetricsManagementException(ex);
        }
    }

    @Override
    public void delete(Metrics metrics) throws MetricsManagementException {
        try {
            metricsRepository.delete(metrics);
        } catch (MetricsRepositoryException ex) {
            throw new MetricsManagementException(ex);
        }
    }
}
