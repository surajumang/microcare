package com.care.form;

import com.care.annotation.Name;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchForm extends FormBean {
    private Logger logger = Logger.getLogger("SearchForm");

    private String email;
    @Name(regex = "(.)+", message = "errors.required")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public ActionErrors validateCustom() {
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "Invok", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "Invok", e);
        }
        return errors;
    }
}