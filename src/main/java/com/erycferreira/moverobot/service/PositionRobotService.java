package com.erycferreira.moverobot.service;

import org.springframework.stereotype.Service;

import com.erycferreira.moverobot.domain.Direction;
import com.erycferreira.moverobot.dto.PositionRobotResponse;
import com.erycferreira.moverobot.exception.RobotOutOfBoundsException;

@Service
public class PositionRobotService {

    public PositionRobotResponse buildPosition(String position) {
        int x = 0;
        int y = 0;
        Direction d = Direction.N;
        String[] coordinates = position.split("");

        for (String coordinate : coordinates) {
            if (coordinate.equals("M")) {
                switch (d) {
                    case N ->
                        y++;
                    case S ->
                        y--;
                    case E ->
                        x++;
                    case W ->
                        x--;
                }
            } else {
                switch (d) {
                    case N ->
                        d = coordinate.equals("L") ? Direction.W : Direction.E;
                    case S ->
                        d = coordinate.equals("L") ? Direction.E : Direction.W;
                    case E ->
                        d = coordinate.equals("L") ? Direction.N : Direction.S;
                    case W ->
                        d = coordinate.equals("L") ? Direction.S : Direction.N;
                }
            }
        }

        if (x < 0 || y < 0 || x > 5 || y > 5) {
            throw new RobotOutOfBoundsException(
                    "the robot cannot walk outside the designated area"
            );
        }

        return new PositionRobotResponse(
                String.format("(%d,%d,%s)", x, y, d),
                x,
                y,
                d.toString()
        );
    }
}
