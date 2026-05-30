package com.conciertos.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> manejarNotFound(
            ResourceNotFoundException ex){

        Map<String,String> error =
                new HashMap<>();

        error.put("mensaje",
                ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> manejarRuntime(
            RuntimeException ex){

        Map<String,String> error =
                new HashMap<>();

        error.put("mensaje",
                ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }
}