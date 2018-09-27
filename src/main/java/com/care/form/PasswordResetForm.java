package com.care.form;

import com.care.annotation.Name;
import com.care.annotation.NotNull;
import com.care.validation.FormValidator;
import com.mysql.jdbc.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordResetForm extends FormBean {
    private Logger logger = Logger.getLogger("PasswordResetForm");

    private String password;
    private String password2;
    private String id;
    private String token;

    @NotNull
    @Name(regex = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]{5,}", required = true, message = "errors.password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
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
        return "PasswordResetForm{" +
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
        // if token is empty then It means it is a password update from the logged in user.
        // if I need to check for the validity of the current password then I need the currently Logged in member.
        // may throw Null pointer exception
        if(! password.equals(password2)){
            errors.add("password2", new ActionMessage("errors.password.mismatch"));
        }
        return errors;
    }

}