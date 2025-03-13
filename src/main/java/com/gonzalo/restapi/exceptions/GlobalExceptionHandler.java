package com.gonzalo.restapi.exceptions;

import com.gonzalo.restapi.io.ErrorObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Global exception handler for all exceptions
 * @author gonzalo
 * */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorObject handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        log.error("Throwing the ResourceNotFoundException from GlobalExceptionHandler {}",exception.getMessage());
        return ErrorObject.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode("DATA_NOT_FOUND")
                .message(exception.getMessage())
                .timestamp(new Date())
                .build();
    }
}
