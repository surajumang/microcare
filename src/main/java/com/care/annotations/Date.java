package com.care.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
    String pattern() default "\\d{2}/\\d{2}/\\d{4}";
    boolean required() default true;
}
