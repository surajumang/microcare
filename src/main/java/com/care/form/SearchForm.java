package com.care.form;

import com.care.validation.FormValidator;
import com.mysql.jdbc.StringUtils;
import org.apache.struts.action.ActionErrors;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class SearchForm extends FormBean {
    private Logger logger = Logger.getLogger("SearchForm");

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (StringUtils.isEmptyOrWhitespaceOnly(email)){
            this.email = "%";
        }else{
            this.email = email;
        }
    }

    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
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
