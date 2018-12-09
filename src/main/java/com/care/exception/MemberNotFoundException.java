package com.care.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }
}
