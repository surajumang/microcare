package com.care.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException() {
    }

    public ApplicationNotFoundException(String message) {
        super(message);
    }

    public ApplicationNotFoundException(Throwable cause) {
        super(cause);
    }
}
