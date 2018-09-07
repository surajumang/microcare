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
        String val = (String)value;
        boolean result = false;
        if (required == false && val == null){
            result = true;
        }
        if (val != null ){
            result = val.matches(regex);
        }
        return result;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
