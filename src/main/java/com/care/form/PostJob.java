package com.care.form;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class PostJob {

    @interface Title{
        boolean NotNull() default true;
    }
    @interface Email{
        String regex();
    }

    @Title(NotNull = true)
    String title;

    @Email(regex = "")
    String email;
}
