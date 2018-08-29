package com.care.validation;

import com.care.annotations.Name;

public final class NameValidator extends Validator{
    private static final NameValidator ourInstance = new NameValidator();

    public static NameValidator getInstance() {
        return ourInstance;
    }

    private NameValidator() {
    }

    @Override
    public boolean validate(String value, String fieldName) {
        String errvalue = "";
        boolean flag = false;
        Name name = (Name) a;

        if(value == null && name.required()){
            errvalue += "Can't be null";
            flag =true;
        }
        if(!value.matches(name.pattern())){
            errvalue += "Not in proper format";
            flag = true;
        }
        if (flag)
            errors.put("name",errvalue);
    }
}
