package com.care.exception;

public class MemberAlreadyRegisteredException extends Exception {

    public MemberAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
