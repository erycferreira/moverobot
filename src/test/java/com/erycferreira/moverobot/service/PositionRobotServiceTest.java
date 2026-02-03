package com.erycferreira.moverobot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.erycferreira.moverobot.dto.PositionRobotResponse;
import com.erycferreira.moverobot.exception.RobotOutOfBoundsException;

class PositionRobotServiceTest {

    private final PositionRobotService service = new PositionRobotService();

    @Test
    void shouldMoveRobotSuccessfully() {
        PositionRobotResponse response = service.buildPosition("MMRMM");

        assertEquals(2, response.x());
        assertEquals(2, response.y());
        assertEquals("E", response.d());
    }

    @Test
    void shouldRotateLeft() {
        PositionRobotResponse response = service.buildPosition("L");

        assertEquals(0, response.x());
        assertEquals(0, response.y());
        assertEquals("W", response.d());
    }

    @Test
    void shouldRotateRight() {
        PositionRobotResponse response = service.buildPosition("R");

        assertEquals(0, response.x());
        assertEquals(0, response.y());
        assertEquals("E", response.d());
    }

    @Test
    void shouldThrowExceptionWhenOutOfBounds() {
        assertThrows(
            RobotOutOfBoundsException.class,
            () -> service.buildPosition("MMMMMMMM")
        );
    }
}
