package com.care.controller;

import com.care.filter.HibernateFilter;
import com.care.form.PasswordForm;
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
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Invalid Password");
        message.put(OperationStatus.SUCCESS, "Password Updated Successfully");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(ControllerUtil.CURRENT_USER);

        String page = "success";
        PasswordForm passwordForm = (PasswordForm) form;
        passwordForm.setId(String.valueOf(member.getId()));

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        OperationStatus operationStatus  =
                authenticationService.updatePassword(member, passwordForm);
        if (operationStatus == OperationStatus.SUCCESS){
            request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "True");
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(operationStatus.name().toLowerCase());
    }
}
