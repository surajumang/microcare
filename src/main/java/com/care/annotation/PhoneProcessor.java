package com.care.annotation;

import java.lang.annotation.Annotation;

import com.care.form.BaseForm;
import com.care.validation.PhoneValidator;
import com.care.validation.Validator;

public class PhoneProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        Phone phone = (Phone) annotation;
        return new PhoneValidator(phone.message());
    }
}
