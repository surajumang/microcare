package com.care.form;

import com.care.annotation.Email;
import com.care.annotation.NotNull;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends FormBean{
    private String email;
    private String password;

    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return errors;
    }

}
