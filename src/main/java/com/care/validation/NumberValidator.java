package com.care.validation;


public class NumberValidator extends Validator {

    String regex;

    public NumberValidator(String regex) {
        this.regex = regex;
    }

    @Override
    public <T> boolean isValid(T value) {
        String val = (String)value;
        return val.matches(regex);
    }
}
