package com.care.dto.form;

import com.care.annotations.NumberCheck;
import com.care.validation.FormBean;

import javax.servlet.http.HttpServletRequest;

public class SitterFilter extends FormBean {
    private String zipCode;
    private String emailPattern;

    @NumberCheck(pattern = "\\d{6}")
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
    public void validate(HttpServletRequest req) {

    }
}
