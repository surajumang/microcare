package com.care.annotation;

import com.care.validation.NameValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class NameProcessor extends AnnotationProcessor {

    @Override
    public <T extends Annotation> Validator create(T annotation) {
        Name name = (Name) annotation;
        return new NameValidator(name.message());
    }
}
