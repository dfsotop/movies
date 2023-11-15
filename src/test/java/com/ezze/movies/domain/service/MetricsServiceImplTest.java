package com.ezze.movies.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ezze.movies.domain.exceptions.MetricsManagementException;
import com.ezze.movies.domain.exceptions.MetricsRepositoryException;
import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.domain.outbound.MetricsRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetricsServiceImplTest {

    @Mock
    MetricsRepository metricsRepository;

    private MetricsService metricsService;

    @BeforeEach
    public void init() {
        metricsService = new MetricsServiceImpl(metricsRepository);
    }

    @Test
    void create_ErrorInPersistence() throws MetricsRepositoryException {
        Metrics d = getTestMetrics();
        when(metricsRepository.create(any())).thenThrow(new MetricsRepositoryException("create test"));
        MetricsManagementException ex = assertThrows(MetricsManagementException.class,
                () -> metricsService.create(d));
        Assertions.assertEquals("create test", ex.getCause().getMessage());
    }
    @Test
    void create_OK() throws MetricsRepositoryException, MetricsManagementException {
        String expectedID = UUID.randomUUID().toString();
        Metrics d = getTestMetrics();
        when(metricsRepository.create(any())).thenReturn(expectedID);
        String actual = metricsService.create(d);
        Assertions.assertEquals(expectedID, actual);
    }

    @Test
    void delete_ErrorInPersistence() throws MetricsRepositoryException {
        Metrics d = getTestMetrics();
        doThrow(new MetricsRepositoryException("delete test")).when(metricsRepository).delete(any());
        MetricsManagementException ex = assertThrows(MetricsManagementException.class,
                () -> metricsService.delete(d));
        Assertions.assertEquals("delete test", ex.getCause().getMessage());
    }

    @Test
    void delete_OK() throws MetricsRepositoryException, MetricsManagementException {
        doNothing().when(metricsRepository).delete(any());
        metricsService.delete(getTestMetrics());
        verify(metricsRepository).delete(any());
    }

    private static Metrics getTestMetrics() {
        return new Metrics("metricsID", "Metrics 1");
    }
}