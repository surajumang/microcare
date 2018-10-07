package com.care.exception;

public class UnauthorizedJobAccessException extends RuntimeException {
    public UnauthorizedJobAccessException() {
    }

    public UnauthorizedJobAccessException(String message) {
        super(message);
    }

    public UnauthorizedJobAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
