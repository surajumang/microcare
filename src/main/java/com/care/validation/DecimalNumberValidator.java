package com.care.validation;

public class DecimalNumberValidator extends NumberValidator {

    private static final String regex = "(\\+|-)?\\d+(\\.\\d+)?";

    public DecimalNumberValidator(String message){
        super(regex, message);
    }

    @Override
    public <T> boolean isValid(T value) {
        return super.isValid(value);
    }
}
