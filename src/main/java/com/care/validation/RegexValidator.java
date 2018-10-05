package com.care.validation;

import com.mysql.jdbc.StringUtils;

public class RegexValidator extends Validator {

    private String regex;
    private String message;

    public RegexValidator(String regex) {
        this.regex = regex;
    }

    public RegexValidator(String regex, String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public <T> boolean isValid(T value) {
        // if value is null then ??
        // it is not it's responsibility to check if null is allowed or not. If null value is not allowed then @NutNull
        // should be used prior to this.
        String userValue = (String) value;
        if (StringUtils.isEmptyOrWhitespaceOnly(userValue)){
            return true;
        }

        return userValue.matches(regex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
