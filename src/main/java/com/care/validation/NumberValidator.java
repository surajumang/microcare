package com.care.validation;


import com.care.annotations.Number;

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
