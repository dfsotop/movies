package com.magellian.movies.application.controllers;

import com.magellian.movies.domain.exceptions.DirectorManagementException;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.service.DirectorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorsController {

    private final DirectorsService directorsService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody @Valid Director director) {
        try {
            String id = directorsService.create(director);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (DirectorManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> get(@PathVariable String id) {
        try {
            Director director = directorsService.getById(id);
            if (director == null) {
                return (ResponseEntity<Director>) ResponseEntity.notFound();
            }
            return ResponseEntity.ok(director);
        } catch (DirectorManagementException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            Director director = directorsService.getById(id);
            if (director == null) {
                return (ResponseEntity<Void>) ResponseEntity.notFound();
            }
            directorsService.delete(director);
            return (ResponseEntity<Void>) ResponseEntity.ok();
        } catch (DirectorManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }

}
