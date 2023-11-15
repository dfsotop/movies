package com.ezze.movies.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.ezze.movies.infrastructure.persistence.MovieDB;

import java.util.List;

public interface MovieRepository extends CrudRepository<MovieDB, Integer> {
    List<MovieDB> getMoviesByRatingGreaterThanEqual(float rating);
    List<MovieDB> getMoviesByMetricsName(String name);
}
