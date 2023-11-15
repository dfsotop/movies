package com.ezze.movies.infrastructure.mappers;

import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.infrastructure.persistence.MetricsDB;

public final class MetricsMapper {

    public static Metrics toModel(MetricsDB metrics) {
        return Metrics.builder()
                .id(metrics.getId())
                .name(metrics.getName())
                .build();
    }

    public static MetricsDB toDataBase(Metrics metrics) {
        return MetricsDB.builder()
                .id(metrics.getId())
                .name(metrics.getName())
                .build();
    }
}
