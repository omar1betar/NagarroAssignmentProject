package com.betar.javaassignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ApiErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public void setErrorResponse(int status, String error, String message,String path) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
