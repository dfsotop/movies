package com.magellian.movies.domain.service;

import com.magellian.movies.domain.exceptions.DirectorManagementException;
import com.magellian.movies.domain.model.Director;

/**
 * Enables the management of directors
 */
public interface DirectorsService {

    /**
     * Enables the creation of a director
     * @param director new director to create
     * @return ID of the created director
     * @throws DirectorManagementException if there was a persistence related error
     */
    String create(Director director) throws DirectorManagementException;

    Director getById(String id) throws DirectorManagementException;
    /**
     * Enables the deletion of a director
     * @param director
     * @throws DirectorManagementException if there was a persistence related error
     */
    void delete(Director director) throws DirectorManagementException;

}
