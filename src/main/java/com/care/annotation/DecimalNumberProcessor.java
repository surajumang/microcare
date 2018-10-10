package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.DecimalNumberValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class DecimalNumberProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        DecimalNumber decimalNumber = (DecimalNumber) annotation;
        return new DecimalNumberValidator(decimalNumber.message());
    }
}
