package com.magellian.movies.domain.service;

import com.magellian.movies.domain.exceptions.MovieManagementException;
import com.magellian.movies.domain.model.Movie;

import java.util.List;

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
     * Obtains a list of movies filtered by director name
     * @param directorName name of the director to filter. Kindly notice the case-sensitive restriction
     * @return List of filtered movies
     * @throws MovieManagementException if there was a persistence related error
     */
    List<Movie> getByDirector(String directorName) throws MovieManagementException;

    /**
     * Obtains a list of movies with a rater equal or greater than the given one.
     * @param rating rating to filter
     * @return List of filtered movies
     * @throws MovieManagementException if there was a persistence related error
     */
    List<Movie> getByRatingGreaterThanOrEqual(int rating) throws MovieManagementException;
}
