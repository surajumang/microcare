package com.care.validation;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

public abstract class FormBean extends ActionForm {
    public abstract ActionErrors validateCustom();

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return validateCustom();
    }
}
