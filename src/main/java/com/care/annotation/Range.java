package com.care.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mysql.jdbc.StringUtils;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Range {
    long min() default Long.MIN_VALUE;
    long max() default Long.MAX_VALUE;
    String message() default "errors.range";
}
