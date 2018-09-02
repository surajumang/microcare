package com.care.validation;

public final class NameValidator extends Validator{

    private String regex;
    private boolean required;

    public NameValidator(String regex, boolean required) {
        this.regex = regex;
        this.required = required;
    }
    
    @Override
    public <T> boolean isValid(T value) {
        String val = (String)value;
        return val.matches(regex);
    }
}
