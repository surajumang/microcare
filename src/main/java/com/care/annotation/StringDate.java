package com.care.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringDate {
    String regex() default "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
    String message() default "errors.date";
    boolean required() default true;
}
