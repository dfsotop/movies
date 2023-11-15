package com.ezze.movies.infrastructure.mappers;

import com.ezze.movies.domain.model.Movie;
import com.ezze.movies.infrastructure.persistence.MovieDB;

public final class MovieMapper {

    public static Movie toModel(MovieDB movieDB) {
        return Movie.builder()
                .id(movieDB.getId())
                .title(movieDB.getTitle())
                .rating(movieDB.getRating())
                .build();
    }
    public static MovieDB toDataBase(Movie movie) {
        return MovieDB.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .rating(movie.getRating())
                .build();
    }
}
