package com.care.validation;

import java.lang.annotation.Annotation;

public class PhoneValidator extends Validator {
    private static final PhoneValidator ourInstance = new PhoneValidator();

    public static PhoneValidator getInstance() {
        return ourInstance;
    }

    private PhoneValidator() {
    }

    @Override
    public void validate(String value, Annotation a) {

    }
}
