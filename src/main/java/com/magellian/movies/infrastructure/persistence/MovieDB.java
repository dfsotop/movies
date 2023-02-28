package com.magellian.movies.infrastructure.persistence;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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

    @ManyToOne(targetEntity = DirectorDB.class, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "director_id", nullable = false)
    private DirectorDB director;
}
