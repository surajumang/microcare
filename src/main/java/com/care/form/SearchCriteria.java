package com.care.form;

import com.care.annotation.Name;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchCriteria extends ActionForm implements FormBean {
    private Logger logger = Logger.getLogger("SearchCriteria");

    private String email;
    @Name(regex = "(.)+", message = "Atleast one character is required")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void validateCustom(ActionErrors errors) {
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "Invok", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "Invok", e);
        }
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();
       validateCustom(errors);
       return errors;
    }
}
