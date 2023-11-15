package com.ezze.movies.infrastructure.persistence;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "METRICS")
@Setter @Getter
@Builder
public class MetricsDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    String name;

    @ManyToOne(targetEntity = MovieDB.class, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieDB movie;

}
