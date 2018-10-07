package com.care.exception;

public class UnauthorizedApplicationAccessException extends RuntimeException {
    public UnauthorizedApplicationAccessException() {
    }

    public UnauthorizedApplicationAccessException(String message) {
        super(message);
    }

    public UnauthorizedApplicationAccessException(Throwable cause) {
        super(cause);
    }
}
