package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.NotNullValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class NotNullProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        NotNull notNull = (NotNull)annotation;
        return new NotNullValidator(notNull.message());
    }
}
