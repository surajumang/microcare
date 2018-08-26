package com.care.validation;

import com.care.annotations.NameCheck;


import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class NameValidator extends Validator{
    private static final NameValidator ourInstance = new NameValidator();

    public static NameValidator getInstance() {
        return ourInstance;
    }

    private NameValidator() {
    }

    @Override
    public void validate(String value, Annotation a, HttpServletRequest req) {

        String errvalue = "";
        boolean flag = false;
        NameCheck nameCheck = (NameCheck) a;
        Map<String, String> myErrors = (Map<String, String>)req.getAttribute("errors");

        if(value == null && nameCheck.required()){
            errvalue += "Can't be null";
            flag =true;
        }
        if(!value.matches(nameCheck.pattern())){
            errvalue += "Not in proper format";
            flag = true;
        }
        if (flag)
            myErrors.put("name",errvalue);

    }
}
