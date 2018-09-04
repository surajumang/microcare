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

    public <T> boolean isValid(T value) {
        boolean status = true;
        String val = (String)value;
        try{
            Date date = new SimpleDateFormat(regex).parse(val);
        }catch (ParseException e){
            status = false;
        }

        return status;
    }
}
