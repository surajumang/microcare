package com.care.dto.form;

import com.care.annotation.Email;
import com.care.annotation.Name;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class LoginDetails extends FormBean {
    private String email;
    private String password;

    @Email()
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Name(regex = "[@#!\\w\\d]{5,}", required = true, message = "Must be 5 or more characters without whitespaces ")
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
    public void validateCustom(Map<String, String> errors) {
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
