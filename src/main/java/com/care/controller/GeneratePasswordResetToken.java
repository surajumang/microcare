package com.care.controller;

import com.care.annotation.Email;
import com.care.model.Member;
import com.care.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GeneratePasswordResetToken extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Email not found.");
        message.put(OperationStatus.SUCCESS, "Password reset link sent to your Email");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = "/visitor/ResetPassword.jsp";
        String email = req.getParameter("email");
        //String regex = "\\w+@([a-z])+(\\.[a-z])+";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        logger.info("Request recieved" + email);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        // check if email exist.
        Member member = accountService.getMember(email);

        if (member != Member.EMPTY_MEMBER){
            logger.info("Member exist for the email "+ email);
            operationStatus = accountService.mailPasswordResetToken(email, req.getContextPath());

        }

        req.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
