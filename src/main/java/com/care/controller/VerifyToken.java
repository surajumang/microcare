package com.care.controller;

import com.care.form.PasswordResetForm;
import com.care.model.Member;
import com.care.model.Status;
import com.care.model.Token;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class VerifyToken extends Action {

    private Logger logger = Logger.getLogger("ResetPassword");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "failure";
        String token = request.getParameter("token");
//        String page = "/visitor/updatePassword.jsp";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        PasswordResetForm passwordResetForm = (PasswordResetForm) form;

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        Token token1 = accountService.getToken(token);

        if (! token1.isEmpty() && !token1.isExpired() && token1.isActive()){
            request.setAttribute("id", token1.getMember().getId());
            request.setAttribute("token", token);
            passwordResetForm.setId(String.valueOf(token1.getMember().getId()));
            passwordResetForm.setToken(token);
            logger.info("member's password resetting");
            operationStatus = OperationStatus.SUCCESS;
            page = "success";
        }
        logger.info("Dispatching to " + page);
        return mapping.findForward(page);
    }
}
