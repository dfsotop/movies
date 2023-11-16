package com.magellian.movies.infrastructure.persistence;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "DIRECTOR")
@Setter @Getter
@Builder
public class DirectorDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    String name;

    @OneToMany(targetEntity = MovieDB.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "director")
    List<MovieDB> movies;

}
