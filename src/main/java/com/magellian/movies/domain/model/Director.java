package com.magellian.movies.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter @Getter
public class Director {
    String id;
    String name;

    public Director(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
