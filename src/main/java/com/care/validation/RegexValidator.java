package com.care.validation;

import org.apache.commons.lang3.StringUtils;

public class RegexValidator extends Validator {

    private String regex;

    public RegexValidator(String regex) {
        this.regex = regex;
    }

    public RegexValidator(String regex, String message) {
        super(message);
        this.regex = regex;
    }

    @Override
    public <T> boolean isValid(T value) {
        // if value is null then ??
        // it is not it's responsibility to check if null is allowed or not. If null value is not
        // allowed then @NutNull
        // should be used prior to this.
        String userValue = (String) value;
        if (StringUtils.isBlank(userValue)){
            return true;
        }
        return userValue.matches(regex);
    }
}
