package com.project.createivewriting.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public BadRequestException(String message){
        super(message);
        this.message = message;
    }

}
