package com.example.patitas.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiExeption(ApiException exeption){
        return ResponseEntity
                .status(exeption.getStatus())
                .body(Map.of(
                        "message", exeption.getMessage(),
                        "status", exeption.getStatus().value()
                ));
    }
    @ExceptionHandler(Exception.class) //No controlados
    public ResponseEntity<?>handleGeneralExeption(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Error interno del servidor",
                        "message", exception.getMessage(),
                        "status", 500
                ));
    }

}
