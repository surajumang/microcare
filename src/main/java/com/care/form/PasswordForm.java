package com.care.form;

import com.care.annotation.Name;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordForm extends FormBean {
    private Logger logger = Logger.getLogger("PasswordForm");

    private String currentPassword;
    private String password;
    private String password2;
    private String id;
    private String token;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Name(regex = "[@#!\\w\\d]{5,}", required = true, message = "error.password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "PasswordForm{" +
                "currentPassword='" + currentPassword + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", id='" + id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public ActionErrors validateCustom() {
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "While validating", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "While validating", e);
        }

        if(! password.equals(password2)){
            errors.add("password2", new ActionMessage("Passwords should match"));
        }
        return errors;
    }

}
