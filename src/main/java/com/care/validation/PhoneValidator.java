package com.care.validation;

public class PhoneValidator extends RegexValidator {

    private static final String regex = "(\\+\\d{2} )?\\d{10}";
    public PhoneValidator(String message) {
        super(regex, message);
    }
}
