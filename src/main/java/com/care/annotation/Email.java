package com.care.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Email {
    String regex() default "(\\w)+@([A-Za-z]+\\.?)+";
    String message() default  "Email is a required field and must be in a proper format";
    boolean required() default true;
}
