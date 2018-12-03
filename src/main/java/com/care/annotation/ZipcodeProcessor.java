package com.care.annotation;

import java.lang.annotation.Annotation;

import com.care.validation.Validator;
import com.care.validation.ZipcodeValidator;

public class ZipcodeProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation) {
        Zipcode zipcode = (Zipcode) annotation;

        return new ZipcodeValidator(zipcode.message());
    }
}
