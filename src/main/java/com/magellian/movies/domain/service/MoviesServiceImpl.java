package com.magellian.movies.domain.service;

import com.magellian.movies.domain.exceptions.MovieManagementException;
import com.magellian.movies.domain.exceptions.MoviesRepositoryException;
import com.magellian.movies.domain.model.Movie;
import com.magellian.movies.domain.outbound.MovieRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {
    private final MovieRepository movieRepository;

    @Override
    public String create(Movie newMovie) throws MovieManagementException {
        try {
            return movieRepository.create(newMovie);
        } catch (MoviesRepositoryException e) {
            throw new MovieManagementException(e);
        }
    }

    @Override
    public Movie getById(String uuid) throws MovieManagementException {
        try {
            return movieRepository.getByID(uuid);
        } catch (MoviesRepositoryException e) {
            throw new MovieManagementException(e);
        }
    }

    @Override
    public Movie update(Movie movieToUpdate) throws MovieManagementException {
        try {
            if (movieRepository.getByID(movieToUpdate.getId()) == null) {
                throw new MovieManagementException("Movie not found");
            }
            return movieRepository.update(movieToUpdate);
        } catch (MoviesRepositoryException e) {
            throw new MovieManagementException(e);
        }
    }

    public void delete(Movie movie) throws MovieManagementException {
        try {
            movieRepository.delete(movie);
        } catch (MoviesRepositoryException e) {
            throw new MovieManagementException(e);
        }
    }

    @Override
    public List<Movie> getByDirector(String directorName) throws MovieManagementException {
        try {
            return movieRepository.getMoviesByDirector(directorName);
        } catch (MoviesRepositoryException e) {
            throw new MovieManagementException(e);
        }
    }

    @Override
    public List<Movie> getByRatingGreaterThanOrEqual(int rating) throws MovieManagementException {
        try {
            return movieRepository.getMoviesByRatingGreaterThan(rating);
        } catch (MoviesRepositoryException e) {
            throw new MovieManagementException(e);
        }
    }
}
