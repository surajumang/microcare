package com.care.annotation;

import com.care.form.BaseForm;
import com.care.validation.RegexValidator;
import com.care.validation.Validator;

import java.lang.annotation.Annotation;

public class RegexProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        Regex regex = (Regex) annotation;
        return new RegexValidator(regex.regex(), regex.message());
    }
}
