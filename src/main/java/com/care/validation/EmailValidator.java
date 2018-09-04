package com.care.validation;

public class EmailValidator extends Validator {

    private String regex;
    private boolean required;
    private String message;

    public EmailValidator(String regex, boolean required, String message) {
        this.regex = regex;
        this.required = required;
        this.message = message;
    }

    @Override
    public <T> boolean isValid(T value) {
        String v = (String)value;
        return v.matches(regex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
