package com.care.validation;

import javax.servlet.http.HttpServletRequest;

public abstract class FormBean {
    public abstract void validate(HttpServletRequest req);

}
