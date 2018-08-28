package com.care.validation;


import com.care.annotations.Number;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Map;

public class NumberValidator extends Validator {
    private static final NumberValidator ourInstance = new NumberValidator();

    public static NumberValidator getInstance() {
        return ourInstance;
    }

    private NumberValidator() {
    }

    @Override
    public void validate(String value, Annotation a, HttpServletRequest req) {
        String errvalue = "";
        boolean flag = false;
        Number number = (Number) a;

        Map<String, String> myErrors = (Map<String, String>)req.getAttribute("errors");

        if(value == null && number.required()){
            errvalue += " can't be NULL";
            flag = true;
        }
        if(!value.matches(number.pattern())){
            errvalue += "Improper format";
            flag = true;
        }
        if (flag)
            myErrors.put("number", errvalue);
    }
}
