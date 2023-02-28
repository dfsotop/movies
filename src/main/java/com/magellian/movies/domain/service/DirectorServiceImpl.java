package com.magellian.movies.domain.service;

import com.magellian.movies.domain.exceptions.DirectorManagementException;
import com.magellian.movies.domain.exceptions.DirectorRepositoryException;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.outbound.DirectorRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorsService {

    private final DirectorRepository directorRepository;

    @Override
    public String create(Director director) throws DirectorManagementException {
        try {
            return directorRepository.create(director);
        } catch (DirectorRepositoryException ex) {
            throw new DirectorManagementException(ex);
        }
    }

    @Override
    public Director getById(String id) throws DirectorManagementException {
        try {
            return directorRepository.getById(id);
        } catch (DirectorRepositoryException ex) {
            throw new DirectorManagementException(ex);
        }
    }

    @Override
    public void delete(Director director) throws DirectorManagementException {
        try {
            directorRepository.delete(director);
        } catch (DirectorRepositoryException ex) {
            throw new DirectorManagementException(ex);
        }
    }
}
