package com.magellian.movies.domain.outbound;

import com.magellian.movies.domain.exceptions.DirectorRepositoryException;
import com.magellian.movies.domain.model.Director;

/**
 * DirectorRepository enables the creation, retrieving and deletion directors
 */
public interface DirectorRepository {

    /**
     * Enables the creation of a new director in the persistence layer
     * @param director director to create
     * @return ID of the created director
     * @throws DirectorRepositoryException if there was a persistence-related error
     */
    String create(Director director) throws DirectorRepositoryException;

    /**
     * Enables the retrieving of a director by a given ID
     * @param id the ID to search
     * @return the found director
     * @throws DirectorRepositoryException if there was a persistence-related error
     */
    Director getById(String id) throws DirectorRepositoryException;

    /**
     * Enables the deletion of a given director
     * @param director director to delete
     * @throws DirectorRepositoryException if there was a persistence-related error
     */
    void delete(Director director) throws DirectorRepositoryException;
}
