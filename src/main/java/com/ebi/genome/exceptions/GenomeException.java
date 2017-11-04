package com.ebi.genome.exceptions;

public class GenomeException extends RuntimeException {
    public GenomeException() {
    }

    public GenomeException(String message) {
        super(message);
    }

    public GenomeException(String message, Throwable cause) {
        super(message, cause);
    }
}
