package com.ezze.movies.domain.service;

import java.util.List;

import com.ezze.movies.domain.exceptions.MovieManagementException;
import com.ezze.movies.domain.model.Movie;

/**
 * MoviesService enables the required CRUD operations to the Movie Model
 * Not in scope:
 */
public interface MoviesService {
    /**
     * Enables the creation of a new movie.
     * @param newMovie movie to create in the system
     * @return ID of the created movie.
     * @throws MovieManagementException if the movie is not correctly defined,
     * or there was persistence related error
     */
    String create(Movie newMovie) throws MovieManagementException;


    /**
     * Enables the retrieval of a given movie.
     * @param uuid of the movie to update.
     * @return the retrieved movie
     * @throws MovieManagementException if there was a persistence related error
     */
    Movie getById(String uuid) throws MovieManagementException;


    /**
     * Enables the update of a given movie.
     * @param movieToUpdate movie to update.
     * @return the updated movie,
     * @throws MovieManagementException if there was a persistence related error
     */
    Movie update(Movie movieToUpdate) throws MovieManagementException;

    /**
     * Enables the deletion of a given movie
     * @param movie the given element to delete
     * @throws MovieManagementException if the given element couldn't be deleted
     */
    void delete(Movie movie) throws MovieManagementException;

    /**
     * Obtains a list of movies filtered by metrics name
     * @param metricsName name of the metrics to filter. Kindly notice the case-sensitive restriction
     * @return List of filtered movies
     * @throws MovieManagementException if there was a persistence related error
     */
    List<Movie> getByMetrics(String metricsName) throws MovieManagementException;

    /**
     * Obtains a list of movies with a rater equal or greater than the given one.
     * @param rating rating to filter
     * @return List of filtered movies
     * @throws MovieManagementException if there was a persistence related error
     */
    List<Movie> getByRatingGreaterThanOrEqual(int rating) throws MovieManagementException;
}
