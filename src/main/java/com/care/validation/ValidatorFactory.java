package com.care.validation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ValidatorFactory {
    private static Logger logger = Logger.getLogger("ValidatorFactory");
    private static final Map<Class<? extends Annotation>, Validator> validators = new HashMap<Class<? extends Annotation>, Validator>();



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

//        if (t instanceof StringDate)
//            isValid = DateValidator.getInstance();
//        if (t instanceof Email)
//            isValid = EmailValidator.getInstance();
//        if (t instanceof Name)
//            isValid = NameValidator.getInstance();
//        if (t instanceof Number)
//            isValid = NumberValidator.getInstance();
//
