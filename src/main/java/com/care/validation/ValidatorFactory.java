package com.care.validation;

import java.lang.annotation.Annotation;

import com.care.annotations.DateCheck;
import com.care.annotations.EmailCheck;
import com.care.annotations.NameCheck;
import com.care.annotations.NumberCheck;
import com.care.annotations.NumberCheck;

public class ValidatorFactory {
    private static Validator v = null;

    public static Validator getInstance(Annotation t){

        if (t instanceof DateCheck)
            v = DateValidator.getInstance();
        if (t instanceof EmailCheck)
            v = EmailValidator.getInstance();
        if (t instanceof NameCheck)
            v = NameValidator.getInstance();
        if (t instanceof NumberCheck)
            v = NumberValidator.getInstance();
//        System.err.println(v);

        return v;
    }
}
