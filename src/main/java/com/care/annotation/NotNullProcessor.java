package com.care.annotation;

import com.care.validation.NotNullValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class NotNullProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation) {
        NotNull notNull = (NotNull)annotation;
        return new NotNullValidator(notNull.message());
    }
}
