package com.care.validation;

import java.util.Map;

public abstract class FormBean {
    public abstract void validateCustom(Map<String, String> errors);

}
