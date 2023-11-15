package com.ezze.movies.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ezze.movies.domain.exceptions.MoviesRepositoryException;
import com.ezze.movies.domain.model.Movie;
import com.ezze.movies.domain.outbound.MovieRepository;
import com.ezze.movies.infrastructure.mappers.MovieMapper;
import com.ezze.movies.infrastructure.persistence.MovieDB;
import com.ezze.movies.infrastructure.persistence.MoviesDBRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoviesRepositoryImpl implements MovieRepository {

    private final MoviesDBRepository databaseRepository;
    @Override
    public String create(Movie newMovie) throws MoviesRepositoryException {
        MovieDB movieDB = MovieMapper.toDataBase(newMovie);
        movieDB = databaseRepository.save(movieDB);
        return movieDB.getId();
    }

    @Override
    public Movie getByID(String id) throws MoviesRepositoryException {
        Optional<MovieDB> optMovieDB = databaseRepository.findById(id);
        MovieDB movieDB = optMovieDB.orElseThrow(() ->new MoviesRepositoryException("movie not found"));
        return MovieMapper.toModel(movieDB);
    }

    @Override
    public Movie update(Movie movieToUpdate) throws MoviesRepositoryException {
        MovieDB movieDB = MovieMapper.toDataBase(movieToUpdate);
        movieDB = databaseRepository.save(movieDB);
        return MovieMapper.toModel(movieDB);
    }

    @Override
    public void delete(Movie movie) throws MoviesRepositoryException {
        databaseRepository.deleteById(movie.getId());
    }

    @Override
    public List<Movie> getMoviesByMetrics(String metricsName) throws MoviesRepositoryException {
        return databaseRepository.getMoviesByMetricsName(metricsName)
                .parallelStream().map(MovieMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByRatingGreaterThan(int rating) throws MoviesRepositoryException {
        return databaseRepository.getMoviesByRatingGreaterThanEqual(rating)
                .parallelStream().map(MovieMapper::toModel).collect(Collectors.toList());
    }
}
