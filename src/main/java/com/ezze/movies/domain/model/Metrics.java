package com.ezze.movies.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter @Getter
@AllArgsConstructor
public class Metrics {
    String id;
    String name;
}
