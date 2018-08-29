package com.care.validation;

import java.lang.annotation.Annotation;

public abstract class Validator {
    public abstract boolean validate(String value, Class<? extends Annotation> annotation);
}
