package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.PasswordValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class PasswordProcessor extends AnnotationProcessor {

    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        Password password = (Password)annotation;
        return new PasswordValidator(password.message());
    }
}
