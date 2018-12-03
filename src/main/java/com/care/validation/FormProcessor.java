package com.care.validation;

import com.care.annotation.Flow;
import com.care.annotation.FlowCheck;
import com.care.form.BaseForm;

import java.lang.reflect.InvocationTargetException;
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
        String methodName = form.getClass().getAnnotation(Flow.class).flowId();
        String value = null;
        try {
          value = (String)form.getClass().getMethod(methodName).invoke(form);
        } catch (IllegalAccessException e) {
          logger.info("Illegal Access" + methodName);
        } catch (InvocationTargetException e) {
          logger.info("Invocation" + methodName);
        } catch (NoSuchMethodException e) {
          logger.info("No Such Method" + methodName);
        }

        String discriminant = value;
        logger.info("Discriminant for the form " + form.getClass().getName() + " is " + discriminant);
        return Stream.of(form.getClass().getMethods())
            .filter(method -> !method.getName().startsWith("get") && testFlow(method, discriminant))
            .collect(Collectors.toSet());
    }

    private static boolean testFlow(Method method, String discriminant){
        boolean check = true;
        FlowCheck annotation = method.getAnnotation(FlowCheck.class);
        if(annotation != null){
            String value = annotation.memberType();
            check = discriminant.equals(value);
        }
        return  check;
    }

}
