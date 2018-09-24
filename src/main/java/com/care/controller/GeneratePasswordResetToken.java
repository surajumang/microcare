package com.care.controller;

import com.care.annotation.Email;
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
        message.put(OperationStatus.FAILURE, "Email not found.");
        message.put(OperationStatus.SUCCESS, "Password reset link sent to your Email");
        message.put(OperationStatus.OTHER, "Enter a Valid Email");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "failure";
        String email = request.getParameter("email");
        String emailError = "This field is required";
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        OperationStatus operationStatus = OperationStatus.FAILURE;
        logger.info("Request recieved" + email);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        // check if email exist.
        boolean match = StringUtils.isEmptyOrWhitespaceOnly(email);

        Member member = null;
        logger.info(match + "email status");
        if (! match){
            if (! email.matches(regex)){
                emailError = "Must be a Valid email";
            }
            else if (accountService.getMember(email) != Member.emptyMember()){
                logger.info("Member exist for the email "+ email);
                operationStatus = accountService.mailPasswordResetToken(email, request.getContextPath());
                if (operationStatus == OperationStatus.SUCCESS){
                    page = "success";
                }
            }
            else {
                emailError = "Email doesn't exist";
            }
        }
        request.setAttribute("emailError", emailError);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(page);
    }
}
