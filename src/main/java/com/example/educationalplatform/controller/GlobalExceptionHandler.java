package com.example.educationalplatform.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid argument", ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid argument type", "Parameter '" + ex.getName() + "' should be of type '" + ex.getRequiredType().getSimpleName() + "'");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFound(NoHandlerFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Endpoint not found", ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", errors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}