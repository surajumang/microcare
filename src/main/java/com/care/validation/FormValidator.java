package com.care.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
    It will iterate through the form's methods(getters) and call the corresponding validation
    depending upon the annotation.
 */
public class FormValidator {
    private static Logger logger = Logger.getLogger("FormValidator");

    public static void  validate(FormBean form){
        Map<String, String> errors = new HashMap<String, String>();

        for(Method method : form.getClass().getMethods()){
            if(method.getName().startsWith("create")){
                String fieldName = method.getName().substring(3);
                fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);

                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    logger.info(method.getName() + "done");
                    logger.info(annotation.toString());
                    Validator v = ValidatorFactory.getInstance(annotation.annotationType());
                    logger.info(v.getClass().getSimpleName());

                    try {
                        String value = (String)method.invoke(form);
                        //v.validate(value, );
                    }catch (IllegalAccessException e){
                        e.getCause();
                    }catch (InvocationTargetException e){
                        e.getCause();
                    }
                }
            }

        }
        form.validateCustom(errors);
    }
}
