package com.care.validation;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

public abstract class Validator {
    public abstract void validate(String value, Annotation a, HttpServletRequest req);
}
