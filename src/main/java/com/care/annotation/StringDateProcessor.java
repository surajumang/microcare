package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.StringDateValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class StringDateProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        StringDate date = (StringDate)annotation;
        return new StringDateValidator(date.message());
    }
}
