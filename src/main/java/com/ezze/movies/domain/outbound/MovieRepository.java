package com.ezze.movies.domain.outbound;

import java.util.List;

import com.ezze.movies.domain.exceptions.MoviesRepositoryException;
import com.ezze.movies.domain.model.Movie;

/**
 * MovieRepository enables the creation, listing,
 * updating and deletion of movies
 */
public interface MovieRepository {

    /**
     * Enables the creation of a given movie
     * @param newMovie the movie to create
     * @return the id of the given movie
     * @throws MoviesRepositoryException if there was a persistence-related error
     */
    String create(Movie newMovie) throws MoviesRepositoryException;

    /**
     * Enables the retrieving of a movie by ID
     * @param id the ID to search
     * @return the found movie
     * @throws MoviesRepositoryException if there was a persistence-related error
     */
    Movie getByID(String id) throws MoviesRepositoryException;

    /**
     * Enables the updating of a given movie
     * @param movieToUpdate movie with new values
     * @return the updated element
     * @throws MoviesRepositoryException if there was a persistence-related error
     */
    Movie update(Movie movieToUpdate) throws MoviesRepositoryException;

    /**
     * Enables the updating of a given movie
     * @param movie movie to delete
     * @throws MoviesRepositoryException if there was a persistence-related error
     */
    void delete(Movie movie) throws MoviesRepositoryException;

    /**
     * Enables the listing of movies By metrics
     * @param metricsName metrics's name to filter
     * @return list of related movies found
     * @throws MoviesRepositoryException if there was a persistence-related error
     */
    List<Movie> getMoviesByMetrics(String metricsName) throws MoviesRepositoryException;

    /**
     * Enables the listing of movies By metrics
     * @param rating rating to filter
     * @return list of related movies found
     * @throws MoviesRepositoryException if there was a persistence-related error
     */
    List<Movie> getMoviesByRatingGreaterThan(int rating) throws MoviesRepositoryException;
}
