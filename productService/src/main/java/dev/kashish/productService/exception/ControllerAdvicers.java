package dev.kashish.productService.exception;

import dev.kashish.productService.dtos.ExceptionsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvicers {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionsDto> notFoundExceptionHandler(NotFoundException notFoundException) {
        return new ResponseEntity<>(new ExceptionsDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
