package com.magellian.movies.infrastructure.mappers;

import com.magellian.movies.domain.model.Director;
import com.magellian.movies.infrastructure.persistence.DirectorDB;

public final class DirectorMapper {

    public static Director toModel(DirectorDB director) {
        return Director.builder()
                .id(director.getId())
                .name(director.getName())
                .build();
    }

    public static DirectorDB toDataBase(Director director) {
        return DirectorDB.builder()
                .id(director.getId())
                .name(director.getName())
                .build();
    }
}
