package com.care.validation;

import com.care.annotations.Email;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Map;

public class EmailValidator extends Validator {
    private static final EmailValidator ourInstance = new EmailValidator();

    public static EmailValidator getInstance() {
        return ourInstance;
    }

    private EmailValidator() {
    }

    @Override
    public void validate(String value, Annotation a, HttpServletRequest req) {

        String errvalue = "";
        boolean flag = false;

        Email emailCheck = (Email)a;
        Map<String, String> myErrors = (Map<String, String>)req.getAttribute("errors");

        if(value == null && emailCheck.required()){
            errvalue += "can't be null";
            flag = true;
        }
        if(!value.matches(emailCheck.pattern())){
            errvalue += "Improper format";
            flag = true;
        }
        if(flag)
            myErrors.put("email", errvalue);

    }
}
