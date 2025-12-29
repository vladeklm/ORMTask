package com.example.educationalplatform.controller;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        this();
        this.status = status;
        this.message = message;
        this.errors = List.of(error);
    }

    // Getters and setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}