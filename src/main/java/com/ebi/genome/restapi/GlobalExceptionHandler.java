package com.ebi.genome.restapi;

import com.ebi.genome.exceptions.IllegalException;
import com.ebi.genome.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Integer CODE_302 = 302;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception e) {
        Message message = new Message();
        message.setCode(CODE_302);
        message.setMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalException.class)
    public ResponseEntity<Object> illegalException(IllegalException e) {
        Message message = new Message();
        message.setCode(e.getCode());
        message.setMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
