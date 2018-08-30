package com.care.exception;

public class IllegalClassTypeException extends IllegalArgumentException {
    public IllegalClassTypeException(){
        super();
    }

    public IllegalClassTypeException(String message){
        super(message);
    }
}
