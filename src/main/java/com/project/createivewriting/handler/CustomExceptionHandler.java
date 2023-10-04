package com.project.createivewriting.handler;

import com.fasterxml.jackson.databind.DatabindException;
import com.project.createivewriting.exception.BadRequestException;
import com.project.createivewriting.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException dataNotFoundException){
        return new ResponseEntity<>(dataNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

}
