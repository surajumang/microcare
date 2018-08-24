package com.care.validation;

import java.lang.annotation.Annotation;

public class EmailValidator extends Validator {
    private static final EmailValidator ourInstance = new EmailValidator();

    public static EmailValidator getInstance() {
        return ourInstance;
    }

    private EmailValidator() {
    }

    @Override
    public void validate(String value, Annotation a) {

    }
}
