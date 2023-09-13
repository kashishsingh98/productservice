package dev.kashish.productService.dtos;

import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
public class ExceptionsDto {
    private HttpStatus errorCode;
    private String message;

    public ExceptionsDto(HttpStatus errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public HttpStatus getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }
}
