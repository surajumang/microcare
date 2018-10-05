package com.care.validation;

public class DecimalNumberValidator extends NumberValidator {

    private static final String regex = "\\d{1,3}(\\.\\d{0,2})?";

    public DecimalNumberValidator(String message){
        super(regex, message);
    }

    @Override
    public <T> boolean isValid(T value) {
        return super.isValid(value);
    }
}
