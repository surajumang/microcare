package com.care.validation;

public abstract class Validator {
    private String message = "errors.default";

    Validator(){

    }

    Validator(String message){
        this.message = message;
    }
    public abstract <T> boolean isValid(T value);

    public String getMessage(){
        return message;
    }

    public boolean addErrorMessage(){
        return true;
    }
}
