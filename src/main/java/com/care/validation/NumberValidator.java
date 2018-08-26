package com.care.validation;


import com.care.annotations.NumberCheck;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
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
        NumberCheck numberCheck = (NumberCheck) a;

        Map<String, String> myErrors = (Map<String, String>)req.getAttribute("errors");

        if(value == null && numberCheck.required()){
            errvalue += " can't be NULL";
            flag = true;
        }
        if(!value.matches(numberCheck.pattern())){
            errvalue += "Improper format";
            flag = true;
        }
        if (flag)
            myErrors.put("number", errvalue);
    }
}
