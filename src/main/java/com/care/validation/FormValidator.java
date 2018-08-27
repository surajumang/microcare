package com.care.validation;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

/*
    It will iterate through the form's methods(getters) and call the corresponding validation
    depending upon the annotation.
 */
public class FormValidator {
    public static void  validate(FormBean form, HttpServletRequest req){
        for(Method method : form.getClass().getMethods()){
            if(!method.getName().startsWith("get")){
                continue;
            }
            for (Annotation annotation : method.getDeclaredAnnotations()
                 ) {
                System.err.println(method.getName() + "done");
//                System.err.println(annotation);

                Validator v = ValidatorFactory.getInstance(annotation);
                System.err.print(v.getClass());
                try {
                    v.validate((String)method.invoke(form), annotation, req);
                }catch (IllegalAccessException e){
                    e.getCause();
                }catch (InvocationTargetException e){
                    e.getCause();
                }
            }
        }
        form.validate(req);
    }
}
