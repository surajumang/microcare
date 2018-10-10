package com.care.annotation;

import com.care.form.BaseForm;
import com.care.model.MemberType;
import com.care.validation.FlowCheckValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FlowCheckProcessor extends AnnotationProcessor {
    /*
    Get the class level annotation @Flow and pass it to the Constructor.
     */
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        FlowCheck flowCheck = (FlowCheck) annotation;
        Flow flow = (Flow) baseForm.getClass().getAnnotation(Flow.class);
        String memberType = null;
        try {
            Method method = baseForm.getClass().getMethod(flow.flowId());
            memberType = (String) method.invoke(baseForm);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new FlowCheckValidator(flowCheck.memberType(), memberType);
    }
}
