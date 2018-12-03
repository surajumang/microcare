package com.care.validation;

public class DecimalNumberValidator extends RegexValidator {

    private static final String regex = "(\\+|-)?\\d+(\\.\\d+)?";

    public DecimalNumberValidator(String message){
        super(regex, message);
    }
}
