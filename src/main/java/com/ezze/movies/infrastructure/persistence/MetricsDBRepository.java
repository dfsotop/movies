package com.ezze.movies.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsDBRepository extends CrudRepository<MetricsDB, String> {
}
