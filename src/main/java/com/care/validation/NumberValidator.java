package com.care.validation;


public class NumberValidator extends Validator {
    private static final NumberValidator ourInstance = new NumberValidator();

    public static NumberValidator getInstance() {
        return ourInstance;
    }

    private NumberValidator() {
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
