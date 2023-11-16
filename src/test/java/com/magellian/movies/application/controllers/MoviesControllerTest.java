package com.magellian.movies.application.controllers;

import com.google.gson.Gson;
import com.magellian.movies.application.controllers.MoviesController;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.model.Movie;
import com.magellian.movies.domain.service.MoviesService;
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
                .director(Director.builder().name("Director 1").build())
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
                .director(Director.builder().name("Director 1").build())
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
    void getByFilterDirector() throws Exception {
        String director = "Director 1";
        Movie movie = Movie.builder().title("movie 1").rating(3)
                .director(Director.builder().name(director).build())
                .build();

        when(moviesService.getByDirector(any())).thenReturn(Collections.singletonList(movie));

        mvc.perform(MockMvcRequestBuilders.get("/api/movies/filters?director="+director)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void getByFilterRating() throws Exception {

        Movie movie = Movie.builder().title("movie 1").rating(3)
                .director(Director.builder().name("Director 1").build())
                .build();

        when(moviesService.getByRatingGreaterThanOrEqual(anyInt())).thenReturn(Collections.singletonList(movie));


        mvc.perform(MockMvcRequestBuilders.get("/api/movies/filters?rating=3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }
}