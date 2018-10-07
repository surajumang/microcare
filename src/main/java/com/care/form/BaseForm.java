package com.care.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseForm extends ActionForm {
    public abstract ActionErrors validateCustom(HttpServletRequest request);

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return validateCustom(request);
    }
}
