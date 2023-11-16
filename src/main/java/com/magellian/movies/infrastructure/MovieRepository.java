package com.magellian.movies.infrastructure;

import com.magellian.movies.infrastructure.persistence.MovieDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<MovieDB, Integer> {
    List<MovieDB> getMoviesByRatingGreaterThanEqual(float rating);
    List<MovieDB> getMoviesByDirectorName(String name);
}
