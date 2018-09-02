package com.care.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDateValidator extends Validator {

    private boolean required;
    private String regex;

    public StringDateValidator(String regex, boolean required){
        this.required = required;
        this.regex = regex;
    }

    public <T> boolean isValid(T value) {
        boolean status = true;
        String val = (String)value;
        try{
            Date date = new SimpleDateFormat(regex).parse(value);
        }catch (ParseException e){
            status = false;
        }

        return status;
    }
}
