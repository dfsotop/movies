package com.ezze.movies.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ezze.movies.domain.outbound.MetricsRepository;
import com.ezze.movies.domain.outbound.MovieRepository;
import com.ezze.movies.domain.service.MetricsServiceImpl;
import com.ezze.movies.domain.service.MetricsService;
import com.ezze.movies.domain.service.MoviesService;
import com.ezze.movies.domain.service.MoviesServiceImpl;

@Configuration
@EnableJpaRepositories("com.ezze.movies.infrastructure.persistence")
@ComponentScan("com.ezze.movies.infrastructure")
@EntityScan("com.ezze.movies.infrastructure")
public class AppConfig {

    @Bean
    public MoviesService moviesService(final MovieRepository moviesProvider) {
        return new MoviesServiceImpl(moviesProvider);
    }

    @Bean
    public MetricsService metricsService(final MetricsRepository metricsRepository) {
        return new MetricsServiceImpl(metricsRepository);
    }
}
