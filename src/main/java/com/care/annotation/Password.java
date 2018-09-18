package com.care.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Password {
    String regex() default "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]{5,}";
    String message() default "errors.password";
    boolean required() default true;
}
