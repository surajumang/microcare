package com.care.annotation;

import java.lang.annotation.Annotation;

import com.care.form.BaseForm;
import com.care.validation.RangeValidator;
import com.care.validation.Validator;

public class RangeProcessor extends AnnotationProcessor {
    @Override
    public <T extends Annotation> Validator create(T annotation, BaseForm baseForm) {
        Range range = (Range) annotation;
        return new RangeValidator(range.min(), range.max(), range.message());
    }
}
