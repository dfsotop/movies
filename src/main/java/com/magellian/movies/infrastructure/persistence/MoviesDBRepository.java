package com.magellian.movies.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesDBRepository extends CrudRepository<MovieDB, String> {
    List<MovieDB> getMoviesByDirectorName(String directorName);

    List<MovieDB> getMoviesByRatingGreaterThanEqual(Integer rate);
}
