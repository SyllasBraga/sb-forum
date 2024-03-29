package com.sb.forum.controller.exceptions;

import com.sb.forum.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFounException(NotFoundException e, HttpServletRequest http){
        StandardError error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                "Not Found", e.getMessage(), http.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationException(MethodArgumentNotValidException e, HttpServletRequest http){

        ValidationError error = new ValidationError(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro na validação", e.getMessage(), http.getRequestURI());

        for (FieldError field : e.getFieldErrors()){
            error.addListErrors(field.getField(), field.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
