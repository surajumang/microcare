package com.care.controller;

import com.care.annotation.Email;
import com.care.exception.BadRequestException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePasswordResetToken extends Action {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "success";

        EmailForm emailForm = (EmailForm)form;
        logger.info("Request recieved" + emailForm.getEmail());

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        OperationStatus operationStatus;
        try {
            operationStatus = accountService.mailPasswordResetToken(emailForm.getEmail(), request.getContextPath());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
            throw new BadRequestException(e);
        }
        if (operationStatus == OperationStatus.SUCCESS){
            page = "success";
        }
        return mapping.findForward(page);
    }
}
