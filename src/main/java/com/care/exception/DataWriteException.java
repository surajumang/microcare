package com.care.exception;

public class DataWriteException extends RuntimeException {
    public DataWriteException() {
    }

    public DataWriteException(String message) {
        super(message);
    }

    public DataWriteException(Throwable cause) {
        super(cause);
    }
}
