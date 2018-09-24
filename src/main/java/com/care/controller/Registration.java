package com.care.controller;

import com.care.form.RegistrationForm;
import com.care.model.MemberType;
import com.mysql.jdbc.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class Registration extends Action {
    Logger logger = Logger.getLogger("Registration");
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrationForm registrationForm = (RegistrationForm) form;
        if(StringUtils.isEmptyOrWhitespaceOnly(registrationForm.getMemberType())){
            registrationForm.setMemberType(MemberType.SEEKER.name());
        }
        return mapping.findForward("success");
    }
}
