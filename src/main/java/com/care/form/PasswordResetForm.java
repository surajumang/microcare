package com.care.form;

import com.care.annotation.NotEmpty;
import com.care.annotation.Password;
import com.care.controller.ControllerUtil;
import com.care.model.Member;
import com.care.service.Hash;
import com.care.validation.FormValidator;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class PasswordResetForm extends BaseForm {
    private Logger logger = Logger.getLogger("PasswordResetForm");

    private String password;
    private String password2;
    private String id;
    private String token;

    @NotEmpty
    @Password(message = "errors.password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
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
    public ActionErrors validateCustom(HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
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
        if (! errors.isEmpty()){
            return errors;
        }

        if(! password.equals(password2)){
            errors.add("password2", new ActionMessage("errors.password.mismatch"));
        }
        else if (member !=null && Hash.createHash(password).equals(member.getPassword())){
            errors.add("password", new ActionMessage("errors.password.same"));
        }
        return errors;
    }

}
