package com.care.exception;

public class DataReadException extends RuntimeException {
    public DataReadException(String message) {
        super(message);
    }

    public DataReadException(Throwable cause) {
        super(cause);
    }
}
