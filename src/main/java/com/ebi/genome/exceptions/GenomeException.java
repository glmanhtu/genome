package com.ebi.genome.exceptions;

import org.springframework.http.HttpStatus;

public class GenomeException extends RuntimeException {

    private HttpStatus httpStatus;

    public GenomeException() {
    }

    public GenomeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
