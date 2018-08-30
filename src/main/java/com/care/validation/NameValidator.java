package com.care.validation;

import com.care.annotations.Name;

public final class NameValidator extends Validator{
    private static final NameValidator ourInstance = new NameValidator();

    public static NameValidator getInstance() {
        return ourInstance;
    }

    private NameValidator() {
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
