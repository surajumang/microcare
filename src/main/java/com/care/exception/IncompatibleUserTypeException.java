package com.care.exception;

public class IncompatibleUserTypeException extends Exception{
    public IncompatibleUserTypeException(){
        super();
    }
    public IncompatibleUserTypeException(String message){
        super(message);
    }
}
