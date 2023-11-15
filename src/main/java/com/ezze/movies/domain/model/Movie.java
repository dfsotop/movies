package com.ezze.movies.domain.model;

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

    private Metrics metrics;
    @Min(1)
    @Max(5)
    private int rating;
}
