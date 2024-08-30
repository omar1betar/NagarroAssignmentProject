package com.betar.javaassignment.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {

        System.out.println(e);
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String,String> errorMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors()  .forEach((error) -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
    }
    //NoSuchElementException
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ApiErrorResponse handleNoSuchElementException(NoSuchElementException ex,WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setErrorResponse(NOT_FOUND.value(), "Not Found",
                ex.getMessage(),request.getDescription(false)

        );
        return errorResponse;
    }

    //MethodArgumentTypeMismatchException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request",
                String.format("Invalid value '%s' for parameter '%s'. Expected type is '%s'.",
                        ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName()),
                request.getDescription(false)
        );
        return errorResponse;
    }
    //UsernameNotFoundException
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiErrorResponse handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setErrorResponse(NOT_FOUND.value(), "Not Found",
                ex.getMessage(),request.getDescription(false)

        );
        return errorResponse;
    }
    //ExpiredJwtException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredJwtException.class)
    public ApiErrorResponse handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setErrorResponse(BAD_REQUEST.value(), "session expired, please login!",
                ex.getMessage(),request.getDescription(false)

        );
        return errorResponse;
    }
    //ServletException
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ServletException.class)
//    public ApiErrorResponse handleServletException(ServletException ex, WebRequest request) {
//        ApiErrorResponse errorResponse = new ApiErrorResponse();
//        errorResponse.setErrorResponse(BAD_REQUEST.value(), "session expired, please login!",
//                ex.getMessage(),request.getDescription(false)
//
//        );
//        return errorResponse;
//    }
}
