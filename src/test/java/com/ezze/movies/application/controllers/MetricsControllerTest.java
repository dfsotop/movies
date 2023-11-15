package com.ezze.movies.application.controllers;

import com.ezze.movies.domain.model.Metrics;
import com.ezze.movies.domain.service.MetricsService;
import com.google.gson.Gson;

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

@WebMvcTest(MetricsController.class)
class MetricsControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    MetricsService metricsService;

    @Test
    void create() throws Exception {
        Gson gson = new Gson();
        Metrics metrics = Metrics.builder().name("metrics1").build();
        String jsonMetrics = gson.toJson(metrics);
        String metricsID = UUID.randomUUID().toString();
        when(metricsService.create(any())).thenReturn(metricsID);

        mvc.perform(MockMvcRequestBuilders.post("/api/metrics/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMetrics)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(metricsID));
    }
}