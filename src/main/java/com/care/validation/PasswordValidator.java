package com.care.validation;

public class PasswordValidator extends RegexValidator {

    private static final String regex = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]{5,}";
    
    public PasswordValidator(String message) {
        super(regex, message);
    }
}
