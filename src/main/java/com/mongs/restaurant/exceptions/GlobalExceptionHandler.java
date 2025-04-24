package com.mongs.restaurant.exceptions;

import com.mongs.restaurant.domain.dtos.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ErrorDto> handleStorageException(StorageException e) {
        log.error("Storage exception occurred: {}", e.getMessage());
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save or return file");
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDto> handleBaseException(BaseException e) {
        log.error("Base exception occurred: {}", e.getMessage());
       return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException occurred: {}", e.getMessage());
        return buildError(HttpStatus.BAD_REQUEST, "Invalid input data.");
    }

    //let's catch all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        log.error("An unexpected error occurred: {}", e.getMessage());
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    public ResponseEntity<ErrorDto> buildError(HttpStatus status, String message) {
        ErrorDto errorDto = ErrorDto.builder()
                .status(status.value())
                .message(message)
                .build();
        return new ResponseEntity<>(errorDto, status);
    }
}
