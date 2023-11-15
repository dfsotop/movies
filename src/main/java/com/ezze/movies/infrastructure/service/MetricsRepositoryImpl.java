package com.ezze.movies.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ezze.movies.domain.exceptions.MetricsRepositoryException;
import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.domain.outbound.MetricsRepository;
import com.ezze.movies.infrastructure.mappers.MetricsMapper;
import com.ezze.movies.infrastructure.persistence.MetricsDB;
import com.ezze.movies.infrastructure.persistence.MetricsDBRepository;

@Service
@RequiredArgsConstructor
public class MetricsRepositoryImpl implements MetricsRepository {

    private final MetricsDBRepository dbRepository;
    @Override
    public String create(Metrics metrics) throws MetricsRepositoryException {
        MetricsDB metricsDB = MetricsMapper.toDataBase(metrics);
        return dbRepository.save(metricsDB).getId();
    }

    @Override
    public Metrics getById(String id) throws MetricsRepositoryException {
        MetricsDB metricsDB = dbRepository
                .findById(id).orElseThrow(()-> new MetricsRepositoryException("not found"));
        return MetricsMapper.toModel(metricsDB);
    }

    @Override
    public void delete(Metrics metrics) throws MetricsRepositoryException {
        dbRepository.deleteById(metrics.getId());
    }
}
