package com.care.controller;

import com.care.annotation.Email;
import com.care.form.EmailForm;
import com.care.model.Member;
import com.care.service.*;
import com.mysql.jdbc.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GeneratePasswordResetToken extends Action {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Couldn't send mail.");
        message.put(OperationStatus.SUCCESS, "Password reset link sent to your Email");
        message.put(OperationStatus.OTHER, "Enter a Valid Email");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "failure";

        EmailForm emailForm = (EmailForm)form;
        String email = request.getParameter("email");

        OperationStatus operationStatus = OperationStatus.FAILURE;
        logger.info("Request recieved" + emailForm.getEmail());

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        operationStatus = accountService.mailPasswordResetToken(emailForm.getEmail(), request.getContextPath());

        if (operationStatus == OperationStatus.SUCCESS){
            page = "success";
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(page);
    }
}
