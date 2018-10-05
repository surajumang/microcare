package com.care.annotation;

import com.care.validation.SizeValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class SizeProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation) {
        Size size = (Size) annotation;
        return new SizeValidator(size.min(), size.max(), size.message());
    }
}
