package com.erycferreira.moverobot.dto;

public record PositionRobotResponse(
    String position,
    int x,
    int y,
    String d
){}