package com.care.validation;

import com.care.annotation.*;
import com.care.annotation.Number;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

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

    private static final Map<Class<? extends Annotation>, AnnotationProcessor> ANNOTATION_PROCESSOR_MAP =
            new HashMap<Class<? extends Annotation>, AnnotationProcessor >();

    static {
        ANNOTATION_PROCESSOR_MAP.put(StringDate.class, new StringDateProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Email.class, new EmailProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Name.class, new NameProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Number.class, new NumberProcessor());
    }

    public static void  validate(FormBean form, ActionErrors errors )
            throws InvocationTargetException, IllegalAccessException {

        logger.info("*****************STaRTING VALIDATION *****************");
        logger.info(form.getClass().getName());

        for(Method method : form.getClass().getMethods()){
            if(method.getName().startsWith("get")){
                //logger.info(method.getName());

                String fieldName = method.getName().substring(3);
                fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);

                for (Annotation annotation : method.getDeclaredAnnotations()) {

                    //logger.info(annotation.toString());
                    Validator v = ANNOTATION_PROCESSOR_MAP.get(annotation.annotationType()).create(annotation);

                    //logger.info(v.getClass().getSimpleName());

                    String value = (String)method.invoke(form);
                    logger.info("" + value);
                    if (! v.isValid(value)){
                        errors.add(fieldName, new ActionMessage(v.getMessage()));
                        logger.info("NOTOKAY" + fieldName);
                    }
                    //logger.info(method.getName() + "done");
                }
            }

        }
        logger.info("***************VALIDATION DONE******************");

    }
}
