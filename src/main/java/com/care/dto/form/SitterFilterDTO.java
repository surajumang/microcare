package com.care.dto.form;

import com.care.annotation.Number;
import com.care.validation.FormBean;

import java.util.Map;

public class SitterFilterDTO extends FormBean {
    private String zipCode;
    private String emailPattern;

    @Number(regex = "\\d{6}")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmailPattern() {
        return emailPattern;
    }

    public void setEmailPattern(String emailPattern) {
        this.emailPattern = emailPattern;
    }

    @Override
    public void validateCustom(Map<String, String> errors) {

    }
}
