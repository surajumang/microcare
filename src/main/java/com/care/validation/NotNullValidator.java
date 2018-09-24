package com.care.validation;

import com.mysql.jdbc.StringUtils;

public class NotNullValidator extends Validator{
    private  String message;

    public NotNullValidator(String message) {
        this.message = message;
    }

    @Override
    public <T> boolean isValid(T value) {
        String stringValue = (String)value;
        return ! StringUtils.isEmptyOrWhitespaceOnly(stringValue);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
