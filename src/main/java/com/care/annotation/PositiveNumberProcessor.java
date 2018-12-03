package com.care.annotation;

import java.lang.annotation.Annotation;

import com.care.validation.PositiveNumberValidator;
import com.care.validation.Validator;

public class PositiveNumberProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation) {
        PositiveNumber positiveNumber = (PositiveNumber) annotation;
        return new PositiveNumberValidator(positiveNumber.includeZero(), positiveNumber.message());
    }
}
