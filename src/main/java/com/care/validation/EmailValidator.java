package com.care.validation;

public class EmailValidator extends Validator {

    private String regex;
    private boolean required;

    public EmailValidator(String regex, boolean required) {
        this.regex = regex;
        this.required = required;
    }

    @Override
    public <T> boolean isValid(T value) {
        String v = (String)value;
        return v.matches(regex);
    }
}
