package com.example.springbootpostgresqldockercompose.exception;

import com.example.springbootpostgresqldockercompose.dto.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse handleBaseException(MyException ex) {
        return GenericResponse
                .failure(HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        GenericResponse response = GenericResponse
                .failure(HttpStatus.BAD_REQUEST.value(),
                        "Validation error!");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            response.addValidationError(fieldName, errorMessage);
        });

        return response;
    }

    @ExceptionHandler(Exception.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse handleException(Exception ex) {
        ex.printStackTrace();

        return GenericResponse
                .failure(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Unknown error!");
    }
}
