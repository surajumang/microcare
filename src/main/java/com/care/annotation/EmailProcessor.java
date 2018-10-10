package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.EmailValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class EmailProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        Email email = (Email)annotation;
        return new EmailValidator(email.message());
    }
}
