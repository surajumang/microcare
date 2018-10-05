package com.care.validation;


public class NumberValidator extends RegexValidator {

    //The choice of at least one digit is appropriate.
    private static final String regex = "\\d+";

    public NumberValidator(String message) {
        super(regex, message);
    }

    public NumberValidator(String regex, String message){
        super(regex, message);
    }
}
