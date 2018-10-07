package com.care.exception;

public class JobExpiredException extends RuntimeException {

    public JobExpiredException(String message) {
        super(message);
    }

    public JobExpiredException(Throwable cause) {
        super(cause);
    }
}
