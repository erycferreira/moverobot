package com.erycferreira.moverobot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erycferreira.moverobot.dto.PositionRobotRequest;
import com.erycferreira.moverobot.dto.PositionRobotResponse;
import com.erycferreira.moverobot.service.PositionRobotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest")
@Validated
public class PositionRobotController {

    private final PositionRobotService service;

    public PositionRobotController(PositionRobotService service) {
        this.service = service;
    }

    @GetMapping("/mars/{position}")
    public ResponseEntity<PositionRobotResponse> position(
            @Valid PositionRobotRequest request
    ) {
        return ResponseEntity.ok(
                service.buildPosition(request.position())
        );
    }
}
