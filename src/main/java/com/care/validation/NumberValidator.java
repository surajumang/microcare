package com.care.validation;


public class NumberValidator extends Validator {

    String regex;
    boolean required;
    String message;

    public NumberValidator(String regex, boolean required, String message) {
        this.regex = regex;
        this.required = required;
        this.message = message;
    }

    @Override
    public <T> boolean isValid(T value) {
        String val = (String)value;
        return val.matches(regex);
    }
}
