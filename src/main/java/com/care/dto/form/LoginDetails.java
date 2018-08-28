package com.care.dto.form;

import com.care.annotations.Email;
import com.care.validation.FormBean;

import javax.servlet.http.HttpServletRequest;

public class LoginDetails extends FormBean {
    private String email;
    private String password;

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDetails{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void validateCustom(HttpServletRequest req) {

    }
}
