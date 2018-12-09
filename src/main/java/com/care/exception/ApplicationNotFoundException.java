package com.care.exception;

public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(Throwable cause) {
        super(cause);
    }
}
