package com.care.validation;

import java.lang.annotation.Annotation;

import com.care.annotations.Date;
import com.care.annotations.Email;
import com.care.annotations.Name;
import com.care.annotations.Number;

public class ValidatorFactory {
    private static Validator v = null;

    public static Validator getInstance(Annotation t){

        if (t instanceof Date)
            v = DateValidator.getInstance();
        if (t instanceof Email)
            v = EmailValidator.getInstance();
        if (t instanceof Name)
            v = NameValidator.getInstance();
        if (t instanceof Number)
            v = NumberValidator.getInstance();
//        System.err.println(v);

        return v;
    }
}
