package com.care.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NameCheck {
    String pattern() default "[A-Za-z]+";
    boolean required() default true;
}
