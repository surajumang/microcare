package com.care.validator;

import java.lang.annotation.Annotation;

public abstract class Validator {
    public abstract void validate(String value, Annotation a);
}
