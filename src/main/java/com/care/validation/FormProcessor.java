package com.care.validation;

import com.care.annotation.Flow;
import com.care.annotation.FlowCheck;
import com.care.form.BaseForm;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FormProcessor {
    private static final Logger logger = Logger.getLogger("FormProcessor");

    /* go through all the methods and add all methods which match
     * @FlowCheck should either match with the proper value or
     * must not exist.
     */
    public static Set<Method> getApplicableMethods(BaseForm form){
        Class<? extends BaseForm> clazz = form.getClass();
        String methodName = clazz.getAnnotation(Flow.class).flowId();
        String value = null;
        try {
          value = (String)clazz.getMethod(methodName).invoke(form);
        } catch (Exception e) {
            logger.info("Can't invoke method passed in Flow" + methodName);
        }

        String discriminant = value;
        logger.info("Discriminant for the form " + clazz.getName() + " is " + discriminant);
        return Stream.of(clazz.getMethods())
            .filter(method -> !method.getName().startsWith("get") && testFlow(method, discriminant))
            .collect(Collectors.toSet());
    }

    private static boolean testFlow(Method method, String discriminant){
        FlowCheck annotation = method.getAnnotation(FlowCheck.class);
        if(annotation != null){
            String value = annotation.flow();
            return discriminant.equals(value);
        }
        return true;
    }

}
