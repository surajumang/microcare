package com.care.annotation;

import com.care.validation.NumberValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class NumberProcessor extends AnnotationProcessor {

    @Override
    public <T extends Annotation> Validator create(T annotation) {
        Number number = (Number)annotation;
        return new NumberValidator(number.message());
    }
}
