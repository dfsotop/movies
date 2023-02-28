package com.magellian.movies.infrastructure.service;

import com.magellian.movies.domain.exceptions.DirectorRepositoryException;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.outbound.DirectorRepository;
import com.magellian.movies.infrastructure.mappers.DirectorMapper;
import com.magellian.movies.infrastructure.persistence.DirectorDB;
import com.magellian.movies.infrastructure.persistence.DirectorDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorRepositoryImpl implements DirectorRepository {

    private final DirectorDBRepository dbRepository;
    @Override
    public String create(Director director) throws DirectorRepositoryException {
        DirectorDB directorDB = DirectorMapper.toDataBase(director);
        return dbRepository.save(directorDB).getId();
    }

    @Override
    public Director getById(String id) throws DirectorRepositoryException {
        DirectorDB directorDB = dbRepository
                .findById(id).orElseThrow(()-> new DirectorRepositoryException("not found"));
        return DirectorMapper.toModel(directorDB);
    }

    @Override
    public void delete(Director director) throws DirectorRepositoryException {
        dbRepository.deleteById(director.getId());
    }
}
