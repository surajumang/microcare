package com.care.validation;

import java.lang.annotation.Annotation;

import com.care.annotations.DateCheck;
import com.care.annotations.EmailCheck;
import com.care.annotations.NameCheck;

public class ValidatorFactory {
    private static Validator v;

    public static Validator getInstance(Class<? extends Annotation> t){
        if (t == DateCheck.class)
            v = DateValidator.getInstance();
        if (t == EmailCheck.class)
            v = EmailValidator.getInstance();
        if (t == NameCheck.class)
            v = NameValidator.getInstance();

        return v;
    }
}
