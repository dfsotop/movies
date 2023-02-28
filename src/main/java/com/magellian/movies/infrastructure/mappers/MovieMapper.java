package com.magellian.movies.infrastructure.mappers;

import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.model.Movie;
import com.magellian.movies.infrastructure.persistence.DirectorDB;
import com.magellian.movies.infrastructure.persistence.MovieDB;

public final class MovieMapper {

    public static Movie toModel(MovieDB movieDB) {
        return Movie.builder()
                .id(movieDB.getId())
                .title(movieDB.getTitle())
                .rating(movieDB.getRating())
                .director(new Director(movieDB.getDirector().getId(), movieDB.getDirector().getName()))
                .build();
    }
    public static MovieDB toDataBase(Movie movie) {
        return MovieDB.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .rating(movie.getRating())
                .director(DirectorDB.builder()
                        .id(movie.getDirector().getId())
                        .name(movie.getDirector().getName())
                        .build())
                .build();
    }
}
