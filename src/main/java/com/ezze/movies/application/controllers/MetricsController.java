package com.ezze.movies.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ezze.movies.domain.exceptions.MetricsManagementException;
import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.domain.service.MetricsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody @Valid Metrics metrics) {
        try {
            String id = metricsService.create(metrics);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (MetricsManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metrics> get(@PathVariable String id) {
        try {
            Metrics metrics = metricsService.getById(id);
            if (metrics == null) {
                return new ResponseEntity<Metrics>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(metrics);
        } catch (MetricsManagementException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            Metrics metrics = metricsService.getById(id);
            if (metrics == null) {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            metricsService.delete(metrics);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (MetricsManagementException e) {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).build();
        }
    }

}
