package com.care.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

/*
    It will iterate through the form's methods(getters) and call the corresponding validation
    depending upon the annotation.
 */
public class FormValidator {
    public static void  validate(FormBean form){
        for(Method method : form.getClass().getMethods()){
            if(!method.getName().startsWith("get")){
                continue;
            }
            for (Annotation annotation : method.getDeclaredAnnotations()
                 ) {
                Validator v = ValidatorFactory.getInstance(annotation.getClass());
                try {
                    v.validate((String)method.invoke(form), annotation);
                }catch (IllegalAccessException e){
                    e.getCause();
                }catch (InvocationTargetException e){
                    e.getCause();
                }
            }
        }
    }
}
