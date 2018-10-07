package com.care.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
    }

    public JobNotFoundException(String message) {
        super(message);
    }

    public JobNotFoundException(Throwable cause) {
        super(cause);
    }
}
