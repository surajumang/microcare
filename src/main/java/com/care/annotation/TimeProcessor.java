package com.care.annotation;

import com.care.validation.TimeValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class TimeProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation) {
        Time time = (Time) annotation;
        return new TimeValidator(time.message());
    }
}
