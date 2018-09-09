package com.care.controller;

import com.care.model.Member;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class VerifyToken extends HttpServlet {

    private Logger logger = Logger.getLogger("UpdatePassword");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Invalid Token");
        message.put(OperationStatus.SUCCESS, "VERIFIED");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String page = "/visitor/UpdatePassword.jsp";
        OperationStatus operationStatus = OperationStatus.FAILURE;

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        Member member = accountService.getMemberUsingToken(token);

        if (member != Member.EMPTY_MEMBER){
            req.setAttribute("id", member.getId());
            req.setAttribute("token", token);
            logger.info("member's password resetting");

            operationStatus = OperationStatus.SUCCESS;
        }
        logger.info("Dispatching to ===" + page);
        req.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
