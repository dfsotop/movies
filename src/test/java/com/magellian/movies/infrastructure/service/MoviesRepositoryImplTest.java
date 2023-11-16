package com.magellian.movies.infrastructure.service;

import com.magellian.movies.domain.exceptions.MoviesRepositoryException;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.model.Movie;
import com.magellian.movies.infrastructure.persistence.DirectorDB;
import com.magellian.movies.infrastructure.persistence.MovieDB;
import com.magellian.movies.infrastructure.persistence.MoviesDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesRepositoryImplTest {

    @Mock
    MoviesDBRepository dbRepository;
    @InjectMocks
    MoviesRepositoryImpl movieRepository;

    @Test
    void create() throws MoviesRepositoryException {
        MovieDB movieDB = getMovieFromDB();
        when(dbRepository.save(any())).thenReturn(movieDB);
        Movie movie = Movie.builder()
                .id("movieID")
                .title("movie1")
                .director(Director.builder().build())
                .rating(2)
                .build();
        ;
        String id = movieRepository.create(movie);
        Assertions.assertEquals("movieID", id);

    }

    @Test
    void getMoviesByDirector() throws MoviesRepositoryException {
        MovieDB movieDB = getMovieFromDB();
        when(dbRepository.getMoviesByDirectorName(any())).thenReturn(Collections.singletonList(movieDB));
        List<Movie> movies = movieRepository.getMoviesByDirector("director1");
        Assertions.assertEquals(1, movies.size());
    }

    @Test
    void getMoviesByRatingGreaterThan() throws MoviesRepositoryException {
        MovieDB movieDB = getMovieFromDB();
        when(dbRepository.getMoviesByRatingGreaterThanEqual(any())).thenReturn(Collections.singletonList(movieDB));
        List<Movie> movies = movieRepository.getMoviesByRatingGreaterThan(2);
        Assertions.assertEquals(1, movies.size());
    }

    private static MovieDB getMovieFromDB() {
        return MovieDB.builder()
                .id("movieID")
                .title("movie1")
                .director(DirectorDB.builder().build())
                .rating(2)
                .build();
    }
}