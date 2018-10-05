package com.care.validation;

public class TimeValidator extends RegexValidator {

    private static final String regex = "\\d{2}:\\d{2}";

    public TimeValidator(String message) {
        super(regex, message);
    }

    @Override
    public <T> boolean isValid(T value) {
        return super.isValid(value);
    }
}
