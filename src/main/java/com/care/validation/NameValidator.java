package com.care.validation;

import com.care.annotations.Name;


import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
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
        Name name = (Name) a;
        Map<String, String> myErrors = (Map<String, String>)req.getAttribute("errors");

        if(value == null && name.required()){
            errvalue += "Can't be null";
            flag =true;
        }
        if(!value.matches(name.pattern())){
            errvalue += "Not in proper format";
            flag = true;
        }
        if (flag)
            myErrors.put("name",errvalue);

    }
}
