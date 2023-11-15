package com.ezze.movies.application.controllers;

import com.ezze.movies.domain.model.Movie;
import com.ezze.movies.domain.service.MoviesService;
import com.google.gson.Gson;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MoviesController.class)
class MoviesControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    MoviesService moviesService;
    @Test
    void create() throws Exception {
        Gson gson = new Gson();
        Movie movie = Movie.builder().title("movie 1").rating(3)
                .build();
        String jsonMovie = gson.toJson(movie);
        String movieID = UUID.randomUUID().toString();
        when(moviesService.create(any())).thenReturn(movieID);

        mvc.perform(MockMvcRequestBuilders.post("/api/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMovie)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(movieID));
    }

    @Test
    void getById() throws Exception {
        Movie movie = Movie.builder().title("movie 1").rating(3)
                .build();
        String movieID = UUID.randomUUID().toString();
        when(moviesService.getById(any())).thenReturn(movie);

        mvc.perform(MockMvcRequestBuilders.get("/api/movies/"+movieID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").exists());
    }


    @Test
    void getByFilterMetrics() throws Exception {
        String metrics = "Metrics 1";
        Movie movie = Movie.builder().title("movie 1").rating(3)
                .build();

        when(moviesService.getByMetrics(any())).thenReturn(Collections.singletonList(movie));

        mvc.perform(MockMvcRequestBuilders.get("/api/movies/filters?metrics="+metrics)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void getByFilterRating() throws Exception {

        Movie movie = Movie.builder().title("movie 1").rating(3)
                .build();

        when(moviesService.getByRatingGreaterThanOrEqual(anyInt())).thenReturn(Collections.singletonList(movie));


        mvc.perform(MockMvcRequestBuilders.get("/api/movies/filters?rating=3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }
}