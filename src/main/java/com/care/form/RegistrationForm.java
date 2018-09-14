package com.care.form;

import com.care.annotation.Email;
import com.care.annotation.Name;
import com.care.annotation.Number;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationForm extends EditProfileForm {

    private Logger logger = Logger.getLogger("RegistrationForm");

    private String password;
    private String password2;

    @Name(regex = "[@#!\\w\\d]{5,}", required = true, message = "Must be 5 or more characters without whitespaces ")
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

    @Override
    public ActionErrors validateCustom() {

        ActionErrors errors = new ActionErrors();
        try {
            errors = super.validateCustom();
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "While validating", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "While validating", e);
        }
        if(! password.equals(password2)){
            errors.add("password2", new ActionMessage("Passwords should match"));
        }
        if (! getEmail().matches("(\\w)+@([A-Za-z]+\\.?)+")){
            errors.add("email", new ActionMessage("errors.email"));
        }
        return errors;
    }
}
