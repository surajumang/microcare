package com.care.validation;

public class NameValidator extends RegexValidator{

    private static final String regex = "[A-Za-z ]+";

    public NameValidator(String message) {
        super(regex, message);
    }
}
