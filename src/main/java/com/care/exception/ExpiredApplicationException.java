package com.care.exception;

public class ExpiredApplicationException extends RuntimeException {
    public ExpiredApplicationException() {
    }

    public ExpiredApplicationException(String message) {
        super(message);
    }

    public ExpiredApplicationException(Throwable cause) {
        super(cause);
    }
}
