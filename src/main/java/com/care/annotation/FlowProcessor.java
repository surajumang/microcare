package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class FlowProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        Flow flow = (Flow) annotation;

        return null;
    }
}
