package com.ezze.movies.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ezze.movies.domain.exceptions.MovieManagementException;
import com.ezze.movies.domain.exceptions.MoviesRepositoryException;
import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.domain.model.Movie;
import com.ezze.movies.domain.outbound.MovieRepository;

@ExtendWith(MockitoExtension.class)
class MoviesServiceImplTest {

    @Mock
    MovieRepository movieRepository;

    private MoviesService moviesService;

    @BeforeEach
    public void init() {
        moviesService = new MoviesServiceImpl(movieRepository);
    }

    @Test
    void create_FailInRepository() throws MoviesRepositoryException {
        when(movieRepository.create(any())).thenThrow(new MoviesRepositoryException("create exception"));
        Movie movie = getNewMovie();
        MovieManagementException ex = assertThrows(MovieManagementException.class, () -> moviesService.create(movie));
        assertEquals("create exception", ex.getCause().getMessage());
    }

    @Test
    void create_OK() throws MovieManagementException, MoviesRepositoryException {
        String expectedID = UUID.randomUUID().toString();
        when(movieRepository.create(any())).thenReturn(expectedID);
        Movie movie = getNewMovie();
        String actualID = moviesService.create(movie);
        assertEquals(expectedID, actualID);
    }

    @Test
    void update_FailInRepository() throws MoviesRepositoryException {
        Movie movie = getNewMovie();
        movie.setId("movie-id");
        when(movieRepository.getByID(any())).thenThrow(new MoviesRepositoryException("getByID on update exception"));
        MovieManagementException ex = assertThrows(MovieManagementException.class, () -> moviesService.update(movie));
        assertEquals("getByID on update exception", ex.getCause().getMessage());
    }

    @Test
    void update_OK_Updated() throws MovieManagementException, MoviesRepositoryException {
        Movie movie = getNewMovie();
        movie.setId("movie-id");
        when(movieRepository.getByID(any())).thenReturn(movie);
        when(movieRepository.update(any())).thenReturn(movie);
        moviesService.update(movie);
        verify(movieRepository).update(any());
    }

    @Test
    void delete_FailInRepository() throws MoviesRepositoryException {
        Movie movie = getNewMovie();
        doThrow(new MoviesRepositoryException("delete exception")).when(movieRepository).delete(any());
        MovieManagementException ex = assertThrows(MovieManagementException.class, () -> moviesService.delete(movie));
        assertEquals("delete exception", ex.getCause().getMessage());
    }

    @Test
    void delete_Ok() throws MovieManagementException, MoviesRepositoryException {
        Movie movie = getNewMovie();
        doNothing().when(movieRepository).delete(movie);
        moviesService.delete(movie);
        verify(movieRepository).delete(any());
    }

    @Test
    void getByMetrics_FailInRepository() throws MoviesRepositoryException {
        when(movieRepository.getMoviesByMetrics(anyString()))
                .thenThrow(new MoviesRepositoryException("getByMetrics exception"));
        MovieManagementException ex = assertThrows(MovieManagementException.class,
                ()-> moviesService.getByMetrics("metrics test"));
        assertEquals("getByMetrics exception", ex.getCause().getMessage());

    }

    @ParameterizedTest(name = "metricsName {0}")
    @MethodSource("getByMetricsFeeder")
    void getByMetrics_OK(String metricsName, List<Movie> expectedMovies) throws MovieManagementException, MoviesRepositoryException {
        when(movieRepository.getMoviesByMetrics(metricsName))
                .thenReturn(expectedMovies);
        List<Movie> actualMovies = moviesService.getByMetrics(metricsName);
        assertIterableEquals(expectedMovies, actualMovies);
    }

    private static Stream<Arguments> getByMetricsFeeder() {
        List<Movie> movies = getPresetMetricsMovies();
        return Stream.of(
                Arguments.of("metrics 1", movies.stream()
                        .collect(Collectors.toList())), // list with 2 elements
                Arguments.of("metrics 2", movies.stream()
                        .collect(Collectors.toList())), // list with 1 element
                Arguments.of("metrics 3",  movies.stream()
                        .collect(Collectors.toList())) // empty list
        );
    }

    @Test
    void getByRatingGreaterThan_FailInRepository() throws MoviesRepositoryException {
        when(movieRepository.getMoviesByRatingGreaterThan(anyInt()))
                .thenThrow(new MoviesRepositoryException("getByRating exception"));
        MovieManagementException ex = assertThrows(MovieManagementException.class,
                ()-> moviesService.getByRatingGreaterThanOrEqual(3));
        assertEquals("getByRating exception", ex.getCause().getMessage());
    }

    @ParameterizedTest(name = "Movie rating {0}")
    @MethodSource("getByRatingFeeder")
    void getByRating_OK(int rating, List<Movie> expectedMovies) throws MovieManagementException, MoviesRepositoryException {
        when(movieRepository.getMoviesByRatingGreaterThan(rating))
                .thenReturn(expectedMovies);
        List<Movie> actualMovies = moviesService.getByRatingGreaterThanOrEqual(rating);
        assertIterableEquals(expectedMovies, actualMovies);
    }

    private static Stream<Arguments> getByRatingFeeder() {
        List<Movie> movies = getPresetMetricsMovies();
        return Stream.of(
                Arguments.of(5, movies.stream()
                        .filter(m -> m.getRating() >= 5)
                        .collect(Collectors.toList())), // empty List.
                Arguments.of(3, movies.stream()
                        .filter(m -> m.getRating() >= 3)
                        .collect(Collectors.toList())), // list with 2 elements
                Arguments.of(1,  movies.stream()
                        .filter(m -> m.getRating() >= 1)
                        .collect(Collectors.toList())) // list with 3 elements
        );
    }

    private static Movie getNewMovie() {
        return Movie.builder().title("movie1").build();
    }

    private static List<Movie> getPresetMetricsMovies() {
        Metrics metrics1 = new Metrics("dir1", "metrics 1");
        Metrics metrics2 = new Metrics("dir2", "metrics 2");

        Movie movie1 = new Movie("123abc", "Movie1", metrics1, 3);
        Movie movie2 = new Movie("456def", "Movie2", metrics1, 4);
        Movie movie3 = new Movie("789ghi", "Movie3", metrics2, 2);

        return Arrays.asList(movie1, movie2, movie3);

    }
}