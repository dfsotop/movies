package com.ezze.movies.infrastructure.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ezze.movies.domain.exceptions.MetricsRepositoryException;
import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.infrastructure.persistence.MetricsDB;
import com.ezze.movies.infrastructure.persistence.MetricsDBRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetricsRepositoryImplTest {

    @Mock
    MetricsDBRepository dbRepository;

    @InjectMocks
    MetricsRepositoryImpl metricsRepository;

    @BeforeEach
    void setup() {

    }
    @Test
    void create() throws MetricsRepositoryException {
        MetricsDB metricsDB = MetricsDB.builder().id("123-abc").name("metrics1").build();
        when(dbRepository.save(any())).thenReturn(metricsDB);
        Metrics metrics = Metrics.builder().name("metrics 1").build();
        String id = metricsRepository.create(metrics);
        Assertions.assertEquals("123-abc", id);
    }

    @Test
    void getById() throws MetricsRepositoryException {
        MetricsDB metricsDB = MetricsDB.builder().id("123-abc").name("metrics1").build();
        when(dbRepository.findById(any())).thenReturn(Optional.of(metricsDB));
        Metrics metrics = metricsRepository.getById("123-abc");
        Assertions.assertNotNull(metrics);
        Assertions.assertEquals("123-abc", metrics.getId());
        Assertions.assertEquals("metrics1", metrics.getName());
    }

    @Test
    void delete() throws MetricsRepositoryException {
        doNothing().when(dbRepository).deleteById(any());
        Metrics metrics = Metrics.builder().id("metricsId").build();
        metricsRepository.delete(metrics);
        verify(dbRepository).deleteById(any());
    }
}