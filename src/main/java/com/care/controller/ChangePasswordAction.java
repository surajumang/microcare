package com.care.controller;

import com.care.form.PasswordUpdateForm;
import com.care.model.Member;
import com.care.service.AuthenticationService;
import com.care.service.AuthenticationServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import com.care.validation.FormPopulator;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        Member member = (Member)request.getSession().getAttribute("currentUser");

        String page = "/member/updatePassword.jsp";
        PasswordUpdateForm passwordUpdateForm = (PasswordUpdateForm) form;
        passwordUpdateForm.setId(String.valueOf(member.getId()));

        OperationStatus operationStatus = OperationStatus.FAILURE;

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        operationStatus = authenticationService.updatePassword(member, passwordUpdateForm);

        page = "/"+ member.getMemberType().name().toLowerCase() + "/home.jsp";

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(page);
    }
}
