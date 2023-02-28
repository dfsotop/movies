package com.magellian.movies.config;

import com.magellian.movies.domain.outbound.DirectorRepository;
import com.magellian.movies.domain.outbound.MovieRepository;
import com.magellian.movies.domain.service.DirectorServiceImpl;
import com.magellian.movies.domain.service.DirectorsService;
import com.magellian.movies.domain.service.MoviesService;
import com.magellian.movies.domain.service.MoviesServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.magellian.movies.infrastructure.persistence")
@ComponentScan("com.magellian.movies.infrastructure")
@EntityScan("com.magellian.movies.infrastructure")
public class AppConfig {

    @Bean
    public MoviesService moviesService(final MovieRepository moviesProvider) {
        return new MoviesServiceImpl(moviesProvider);
    }

    @Bean
    public DirectorsService directorsService(final DirectorRepository directorRepository) {
        return new DirectorServiceImpl(directorRepository);
    }
}
