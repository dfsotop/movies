package com.magellian.movies.application.controllers;

import com.magellian.movies.domain.exceptions.MovieManagementException;
import com.magellian.movies.domain.model.Movie;
import com.magellian.movies.domain.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesService moviesService;

    @PostMapping("/")
    public ResponseEntity<String> createMovie(@RequestBody @Valid Movie movie) {
        try {
            String id = moviesService.create(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (MovieManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable(name = "id") String uuid) {
        try {
           Movie movie = moviesService.getById(uuid);
            return ResponseEntity.ok(movie);
        } catch (MovieManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<Movie> updateMovie(@RequestBody @Valid Movie movie) {
        try {
            Movie updated = moviesService.update(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(updated);
        } catch (MovieManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }

    @GetMapping(value = "/filters")
    public ResponseEntity<List<Movie>> getMoviesByFilter(
            @RequestParam(required = false, name = "director") String directorName,
            @RequestParam(required = false, name = "rating") Integer rating) {
        try {
            if ((directorName != null && rating != null) || (directorName == null && rating == null)) {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON).build();
            }
            if (directorName != null) {
                return ResponseEntity.ok(moviesService.getByDirector(directorName));
            } else {
                return ResponseEntity.ok(moviesService.getByRatingGreaterThanOrEqual(rating));
            }
        } catch (MovieManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }
}
