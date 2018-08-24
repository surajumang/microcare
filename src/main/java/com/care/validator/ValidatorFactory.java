package com.care.validator;

import java.lang.annotation.Annotation;

import com.care.annotations.DateCheck;
import com.care.annotations.EmailCheck;
import com.care.annotations.NameCheck;

public class ValidatorFactory {
    private Validator;

    public static Validator getInstance(Class<? extends Annotation> t){
        if (t == DateCheck.class)
            return DateValidator.getInstance();
        if (t == EmailCheck.class)
            return EmailValidator.getInstance();
        if (t == NameCheck.class)
            return NameValidator.getInstance();


    }
}
