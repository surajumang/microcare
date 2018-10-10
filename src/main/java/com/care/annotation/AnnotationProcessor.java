package com.care.annotation;

import com.care.form.BaseForm;
import com.care.form.EmailForm;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public abstract class AnnotationProcessor {
    public <T extends Annotation> Validator create(T annotation) {
        return create(annotation, new EmailForm());
    }

    public abstract <T extends Annotation> Validator create(T annotation, BaseForm baseForm);
}
