package com.care.validation;

import java.lang.annotation.Annotation;

public abstract class Validator {
    public abstract void validate(String value, Annotation a);
}
