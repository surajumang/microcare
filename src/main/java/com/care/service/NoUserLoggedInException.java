package com.care.service;

public class NoUserLoggedInException extends Exception{
    public NoUserLoggedInException(String message) {
        super(message);
    }
}
