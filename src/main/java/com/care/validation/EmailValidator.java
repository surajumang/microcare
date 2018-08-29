package com.care.validation;

import com.care.annotations.Email;

public class EmailValidator extends Validator {
    private static final EmailValidator ourInstance = new EmailValidator();

    public static EmailValidator getInstance() {
        return ourInstance;
    }

    private EmailValidator() {
    }

    @Override
    public boolean validate(String value, String fieldName) {
        String errvalue = "";
        boolean flag = false;

        Email emailCheck = (Email)a;

        if(value == null && emailCheck.required()){
            errvalue += "can't be null";
            flag = true;
        }
        if(!value.matches(emailCheck.pattern())){
            errvalue += "Improper format";
            flag = true;
        }
        if(flag)
            errors.put(fieldName, errvalue);
    }
}
