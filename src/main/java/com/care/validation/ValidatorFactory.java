package com.care.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class ValidatorFactory {
    private static Validator v = null;

    public static Validator getInstance(Annotation t){
        Validator returnValue = null;
        String className = t.annotationType().getSimpleName().concat("Validaor");
        // log it
        try{
            Class clazz = Class.forName(className);
            java.lang.reflect.Method method = clazz.getMethod("getInstance");
            returnValue = (Validator)method.invoke(null);
        }catch (ClassNotFoundException e){
            e.getCause();
        }catch (NoSuchMethodException e){
            e.getCause();
        }catch (IllegalAccessException e){
            e.getCause();
        }catch (InvocationTargetException e){
            e.getCause();
        }

//        if (t instanceof Date)
//            v = DateValidator.getInstance();
//        if (t instanceof Email)
//            v = EmailValidator.getInstance();
//        if (t instanceof Name)
//            v = NameValidator.getInstance();
//        if (t instanceof Number)
//            v = NumberValidator.getInstance();
//        System.err.println(v);

        return v;
    }
}
