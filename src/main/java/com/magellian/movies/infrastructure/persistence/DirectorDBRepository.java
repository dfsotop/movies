package com.magellian.movies.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorDBRepository extends CrudRepository<DirectorDB, String> {
}
