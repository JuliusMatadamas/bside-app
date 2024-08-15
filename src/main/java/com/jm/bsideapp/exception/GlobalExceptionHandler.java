package com.jm.bsideapp.exception;

import com.jm.bsideapp.model.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleServiceExceptions(StudentServiceException ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), List.of());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(StudentNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), List.of());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
