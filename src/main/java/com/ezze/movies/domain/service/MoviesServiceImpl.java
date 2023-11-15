package com.ezze.movies.domain.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.ezze.movies.domain.exceptions.MovieManagementException;
import com.ezze.movies.domain.exceptions.MoviesRepositoryException;
import com.ezze.movies.domain.model.Movie;
import com.ezze.movies.domain.outbound.MovieRepository;

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
    public List<Movie> getByMetrics(String metricsName) throws MovieManagementException {
        try {
            return movieRepository.getMoviesByMetrics(metricsName);
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
