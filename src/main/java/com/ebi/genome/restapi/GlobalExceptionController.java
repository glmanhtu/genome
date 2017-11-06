package com.ebi.genome.restapi;

import com.ebi.genome.exceptions.GenomeException;
import com.ebi.genome.utils.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(GenomeException.class)
    public ResponseEntity<Object> exception(GenomeException e) {
        Message message = new Message();
        message.setCode(e.getHttpStatus().value());
        message.setMessage(e.getMessage());
        return new ResponseEntity<>(message, e.getHttpStatus());
    }
}
