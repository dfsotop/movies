package com.magellian.movies.domain.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Movie {

    private String id;
    @NotNull
    private String title;

    @NotNull
    private Director director;
    @Min(1)
    @Max(5)
    private int rating;
}
