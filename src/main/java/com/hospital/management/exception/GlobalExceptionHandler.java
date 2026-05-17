package com.hospital.management.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException (ResourceNotFoundException e) {
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler (BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException (BusinessException e) {
        ErrorResponse error = ErrorResponse.builder()
                .status(e.getStatusCode())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(e.getStatusCode()).body(error);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidException (MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .getFirst()
                .getDefaultMessage();

        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException (Exception e) {
        ErrorResponse error = ErrorResponse.builder()
                .status(500)
                .message("Internal Server Error")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(500).body(error);
    }
}
