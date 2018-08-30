package com.care.service;

public class NoUserLoggedInException extends RuntimeException{
    public NoUserLoggedInException(String message) {
        super(message);
    }
}
