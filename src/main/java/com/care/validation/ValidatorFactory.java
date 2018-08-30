package com.care.validation;

import com.care.annotations.Date;
import com.care.annotations.Email;
import com.care.annotations.Name;
import com.care.annotations.Number;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidatorFactory {
    private static Logger logger = Logger.getLogger("ValidatorFactory");
    private static final Map<Class<? extends Annotation>, Validator> validators = new HashMap<Class<? extends Annotation>, Validator>();

    static {
        validators.put(Date.class, DateValidator.getInstance());
        validators.put(Email.class, EmailValidator.getInstance());
        validators.put(Name.class, NameValidator.getInstance());
        validators.put(Number.class, NumberValidator.getInstance());
    }

// call it using annotationType() method
    public static Validator getInstance(Class<? extends Annotation> annotationType) {
        logger.info(annotationType.toString());
        Validator returnValue = null;

        return validators.get(annotationType);
    }
}

//        String className = t.annotationType().getSimpleName().concat("Validaor");
//
//        try{
//            Class clazz = Class.forName(className);
//            java.lang.reflect.Method method = clazz.getMethod("getInstance");
//            returnValue = (Validator)method.invoke(null);
//        }

//        if (t instanceof Date)
//            isValid = DateValidator.getInstance();
//        if (t instanceof Email)
//            isValid = EmailValidator.getInstance();
//        if (t instanceof Name)
//            isValid = NameValidator.getInstance();
//        if (t instanceof Number)
//            isValid = NumberValidator.getInstance();
//
