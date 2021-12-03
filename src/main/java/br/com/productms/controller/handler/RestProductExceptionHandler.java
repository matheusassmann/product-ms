package br.com.productms.controller.handler;

import br.com.productms.domain.error.ApiError;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestProductExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ApiError handleObjectNotFoundException(ObjectNotFoundException exception){
        return ApiError.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getEntityName())
                .build();
    }
}
