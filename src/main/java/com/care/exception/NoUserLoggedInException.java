package com.care.exception;

public class NoUserLoggedInException extends Exception{
    public NoUserLoggedInException(){
        super();
    }
    public NoUserLoggedInException(String message) {
        super(message);
    }
}
