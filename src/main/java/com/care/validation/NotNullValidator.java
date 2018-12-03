package com.care.validation;

import com.mysql.jdbc.StringUtils;

public class NotNullValidator extends Validator{

    public NotNullValidator(String message) {
        super(message);
    }

    @Override
    public <T> boolean isValid(T value) {
        String stringValue = (String)value;
        return ! StringUtils.isEmptyOrWhitespaceOnly(stringValue);
    }
}
