package com.ebi.genome.utils;

import java.io.Serializable;

public class Message implements Serializable {

    /** The Serial Version UID for Serializable classes */
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private String link;

    public Message() {
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
