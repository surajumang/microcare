package com.care.validation;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class FormPopulator {
    public static FormBean populate(HttpServletRequest request, Class<? extends FormBean> form){
        /*
            Go through the methods/fields of form and find the same in the request scope.
         */
        FormBean ret = null;
        try {
            ret = form.newInstance();
        }catch (InstantiationException e){
            e.getCause();
        }
        catch (IllegalAccessException e){
            e.getCause();
        }

        for (Method method: form.getMethods()) {
            String methodName = method.getName();
            if (methodName.startsWith("set")){
                String fieldName = methodName.substring(3);
                Logger logger = Logger.getLogger(FormPopulator.class.getName());
                logger.info("fieldName before -->" + fieldName);
                fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);

                logger.info("fieldName after -->" + fieldName);
                try {
                    method.invoke(ret,(String)request.getParameter(fieldName));
                }catch (IllegalAccessException e){
                    e.getCause();
                }catch (InvocationTargetException e){
                    e.getCause();
                }
            }
        }
        return ret;
    }
}
