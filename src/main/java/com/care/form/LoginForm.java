package com.care.form;

import com.care.annotation.Email;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;

import java.lang.reflect.InvocationTargetException;

public class LoginForm extends FormBean{
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
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public ActionErrors validateCustom() {
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
