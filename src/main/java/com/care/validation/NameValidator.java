package com.care.validation;

public final class NameValidator extends Validator{

    private String regex;
    private boolean required;
    private String message;

    public NameValidator(String regex, boolean required, String message) {
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
