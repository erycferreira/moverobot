package com.erycferreira.moverobot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PositionRobotRequest(
    @NotBlank(message = "position is required")
    @Size(min = 1, max = 60, message = "position must have between 1 and 60 characters")
    @Pattern(
        regexp = "^[LRMlr]+$",
        message = "to position only L,R or M"
    )
    String position
){}