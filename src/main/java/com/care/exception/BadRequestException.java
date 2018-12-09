package com.care.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
