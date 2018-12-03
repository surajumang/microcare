package com.care.annotation;

import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public abstract class AnnotationProcessor {
    public abstract <T extends Annotation> Validator create(T annotation);
}
