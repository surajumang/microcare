package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.TimeValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class TimeProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        Time time = (Time) annotation;
        return new TimeValidator(time.message());
    }
}
