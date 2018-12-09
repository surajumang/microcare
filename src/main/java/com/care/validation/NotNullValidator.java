package com.care.validation;

import org.apache.commons.lang3.StringUtils;

public class NotNullValidator extends Validator{

    public NotNullValidator(String message) {
        super(message);
    }

    @Override
    public <T> boolean isValid(T value) {
        String stringValue = (String)value;
        return ! StringUtils.isBlank(stringValue);
    }
}
