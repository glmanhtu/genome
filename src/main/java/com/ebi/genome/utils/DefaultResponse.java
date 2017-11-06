package com.ebi.genome.utils;

/**
 * Created by optimize on 4/22/17.
 */

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DefaultResponse {

    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    private String message;
    private List<String> exceptions;

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(final List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public DefaultResponse() {
    }

    private DefaultResponse(String message) {
        this.message = message;
    }
    private DefaultResponse(String message, List<String> exceptions) {
        this.exceptions = exceptions;
        this.message = message;
    }

    private DefaultResponse(String message, Exception e) {
        exceptions = new ArrayList<>();
        exceptions.add(e.getMessage());
        this.message = message;
    }

    public static ResponseEntity<?> success(Object response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<?> success(String response) {
        return new ResponseEntity<Object>(new DefaultResponse(response), HttpStatus.OK);
    }

    public static ResponseEntity<?> success(Object response, HttpHeaders headers) {
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    public static ResponseEntity<?> success() {
        return new ResponseEntity<>(new DefaultResponse(SUCCESS), HttpStatus.OK);
    }

    public static ResponseEntity<?> failed() {
        return new ResponseEntity<>(new DefaultResponse(FAILED), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> failed(HttpStatus status) {
        return new ResponseEntity<>(new DefaultResponse(FAILED), status);
    }

    public static ResponseEntity<?> failed(List<String> exceptions) {
        return new ResponseEntity<>(new DefaultResponse(FAILED, exceptions), HttpStatus.OK);
    }

    public static ResponseEntity<?> failed(Exception exception) {
        return new ResponseEntity<>(new DefaultResponse(FAILED, exception), HttpStatus.OK);
    }


}
