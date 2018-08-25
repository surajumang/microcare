package com.care.validation;

import com.care.annotations.NameCheck;

import java.lang.annotation.Annotation;

public class NameValidator extends Validator{
    private static final NameValidator ourInstance = new NameValidator();

    public static NameValidator getInstance() {
        return ourInstance;
    }

    private NameValidator() {
    }

    @Override
    public void validate(String value, Annotation a) {
        NameCheck namePattern = (NameCheck)a;
        String pattern = namePattern.pattern();
        /*
        Write code to check if the given string conforms to the pattern
        If not write an entry to the session object
        The entry could be an ArrayList<Map<String>>.
         */

    }
}
