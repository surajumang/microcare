package com.care.validation;

import java.lang.annotation.Annotation;

public class NameValidator extends Validator{
    private static NameValidator ourInstance = new NameValidator();

    public static NameValidator getInstance() {
        return ourInstance;
    }

    private NameValidator() {
    }

    @Override
    public void validate(String value, Annotation a) {

    }
}
