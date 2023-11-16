package com.magellian.movies.application.controllers;

import com.google.gson.Gson;
import com.magellian.movies.application.controllers.DirectorsController;
import com.magellian.movies.domain.model.Director;
import com.magellian.movies.domain.service.DirectorsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DirectorsController.class)
class DirectorsControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    DirectorsService directorsService;

    @Test
    void create() throws Exception {
        Gson gson = new Gson();
        Director director = Director.builder().name("director1").build();
        String jsonDirector = gson.toJson(director);
        String directorID = UUID.randomUUID().toString();
        when(directorsService.create(any())).thenReturn(directorID);

        mvc.perform(MockMvcRequestBuilders.post("/api/directors/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDirector)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(directorID));
    }
}