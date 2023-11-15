package com.ezze.movies.infrastructure.persistence;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "MOVIE")
@Setter @Getter
@Builder
public class MovieDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String title;
    private int rating;

    @OneToMany(targetEntity = MetricsDB.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "movie")
    List<MetricsDB> metrics;
    
}
