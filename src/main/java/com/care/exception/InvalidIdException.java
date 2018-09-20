package com.care.exception;

public class InvalidIdException extends IllegalArgumentException {
    public InvalidIdException() {
    }

    public InvalidIdException(String s) {
        super(s);
    }

    public InvalidIdException(Throwable cause) {
        super(cause);
    }
}
