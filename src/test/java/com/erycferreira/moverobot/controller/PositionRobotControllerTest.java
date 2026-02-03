package com.erycferreira.moverobot.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.erycferreira.moverobot.dto.PositionRobotResponse;
import com.erycferreira.moverobot.service.PositionRobotService;

@WebMvcTest(PositionRobotController.class)
class PositionRobotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PositionRobotService service;

    @Test
    void shouldReturn200WhenValidInput() throws Exception {
        Mockito.when(service.buildPosition("MMR"))
                .thenReturn(new PositionRobotResponse("(1,2,E)", 1, 2, "E"));

        mockMvc.perform(get("/rest/mars/MMR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x").value(1))
                .andExpect(jsonPath("$.y").value(2))
                .andExpect(jsonPath("$.d").value("E"));
    }

    @Test
    void shouldReturn400WhenInvalidCommand() throws Exception {
        mockMvc.perform(get("/rest/mars/ABC"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("validation_error"));
    }
}
