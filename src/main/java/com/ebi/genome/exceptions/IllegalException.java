package com.ebi.genome.exceptions;

import com.ebi.genome.utils.Message;

public class IllegalException extends GenomeException {

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
