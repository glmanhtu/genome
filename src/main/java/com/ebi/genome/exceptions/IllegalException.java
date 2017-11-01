package com.ebi.genome.exceptions;

import com.ebi.genome.utils.Message;

/**
 * Created by greenlucky on 4/14/17.
 */
public class IllegalException extends RuntimeException {

    private String message;
    private int code;

    public IllegalException(String message) {
        super(message);
        this.message = message;
    }

    public IllegalException(Message message) {
        super(message.getMessage());
        this.message = message.getMessage();
        this.code = message.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
