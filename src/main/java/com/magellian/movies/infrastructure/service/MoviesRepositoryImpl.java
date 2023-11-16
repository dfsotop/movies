package com.magellian.movies.infrastructure.service;

import com.magellian.movies.domain.exceptions.MoviesRepositoryException;
import com.magellian.movies.domain.model.Movie;
import com.magellian.movies.domain.outbound.MovieRepository;
import com.magellian.movies.infrastructure.mappers.MovieMapper;
import com.magellian.movies.infrastructure.persistence.MovieDB;
import com.magellian.movies.infrastructure.persistence.MoviesDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Movie> getMoviesByDirector(String directorName) throws MoviesRepositoryException {
        return databaseRepository.getMoviesByDirectorName(directorName)
                .parallelStream().map(MovieMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByRatingGreaterThan(int rating) throws MoviesRepositoryException {
        return databaseRepository.getMoviesByRatingGreaterThanEqual(rating)
                .parallelStream().map(MovieMapper::toModel).collect(Collectors.toList());
    }
}
