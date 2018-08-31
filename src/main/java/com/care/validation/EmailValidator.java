package com.care.validation;

public class EmailValidator extends Validator {
    private static final EmailValidator ourInstance = new EmailValidator();

    public static EmailValidator getInstance() {
        return ourInstance;
    }

    private EmailValidator() {
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
