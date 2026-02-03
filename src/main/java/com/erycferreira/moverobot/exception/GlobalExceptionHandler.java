package com.erycferreira.moverobot.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Erro de validação");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "validation_error",
                        "message", message
                ));
    }

    @ExceptionHandler(RobotOutOfBoundsException.class)
    public ResponseEntity<?> handleRobotOut(RobotOutOfBoundsException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "error", "robot_out_of_bounds",
                        "message", ex.getMessage()
                ));
    }
}
