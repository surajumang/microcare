package com.care.controller;

import com.care.filter.HibernateFilter;
import com.care.form.PasswordResetForm;
import com.care.form.PasswordUpdateForm;
import com.care.model.Member;
import com.care.service.AuthenticationService;
import com.care.service.AuthenticationServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ChangePasswordAction extends Action {
    Logger logger = Logger.getLogger("ChangePasswordAction");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(ControllerUtil.CURRENT_USER);

        String page = "success";
        PasswordUpdateForm passwordUpdateForm = (PasswordUpdateForm) form;
        passwordUpdateForm.setId(String.valueOf(member.getId()));

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        OperationStatus operationStatus  =
                authenticationService.updatePassword(member, passwordUpdateForm);
        if (operationStatus == OperationStatus.SUCCESS){
            request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "True");
        }
        return mapping.findForward(operationStatus.name().toLowerCase());
    }
}
