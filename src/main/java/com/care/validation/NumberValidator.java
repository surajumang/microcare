package com.care.validation;


import com.care.annotations.Number;

public class NumberValidator extends Validator {
    private static final NumberValidator ourInstance = new NumberValidator();

    public static NumberValidator getInstance() {
        return ourInstance;
    }

    private NumberValidator() {
    }

    @Override
    public boolean validate(String value, String fieldName) {
        String errvalue = "";
        boolean flag = false;
        Number number = (Number) a;

        if(value == null && number.required()){
            errvalue += " can't be NULL";
            flag = true;
        }
        if(!value.matches(number.pattern())){
            errvalue += "Improper format";
            flag = true;
        }
        if (flag)
            errors.put("number", errvalue);
    }
}
