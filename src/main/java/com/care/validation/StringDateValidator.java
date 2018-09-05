package com.care.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDateValidator extends Validator {

    private boolean required;
    private String regex;
    private String message;

    public StringDateValidator(String regex, boolean required, String message){
        this.required = required;
        this.regex = regex;
        this.message = message;
    }

    @Override
    public <T> boolean isValid(T value) {
        String val = (String)value;
        boolean result = false;

        if (!required){
            result = true;
        }
        if (val != null ){
            result = val.matches(regex);
        }

        return result;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
