package com.erycferreira.moverobot.exception;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.erycferreira.moverobot.controller.PositionRobotController;
import com.erycferreira.moverobot.service.PositionRobotService;

@WebMvcTest(PositionRobotController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PositionRobotService service;

    @Test
    void shouldHandleOutOfBoundsException() throws Exception {
        Mockito.when(service.buildPosition("MMMMMMMM"))
                .thenThrow(new RobotOutOfBoundsException("the robot cannot walk outside the designated area"));

        mockMvc.perform(get("/rest/mars/MMMMMMMM"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("the robot cannot walk outside the designated area"));
    }
}
