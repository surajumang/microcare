package com.care.validation;

public class ZipcodeValidator extends RegexValidator {
    private static final String regex = "\\d{6}";
    public ZipcodeValidator(String message) {
        super(regex, message);
    }
}
