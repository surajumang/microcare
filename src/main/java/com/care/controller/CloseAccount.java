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

public class CloseAccount extends HttpServlet {
    Logger logger = Logger.getLogger("CloseAccount");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Couldn't delete");
        message.put(OperationStatus.SUCCESS, "Successfully Closed Account");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        First Sets the member status to inactive and then dispatches the rquest to Logout servlet.
         */

        Member currentUser = (Member) request.getSession().getAttribute("currentUser");
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        String page = "/"+currentUser.getMemberType().name().toLowerCase()+"/Home.jsp";

        logger.info("Member deleted successfully");
        OperationStatus operationStatus = accountService.deleteMember(currentUser);
        // take to login page when closed.
        if (operationStatus == OperationStatus.SUCCESS){
            page="/index.jsp";
            request.getSession().invalidate();
        }
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(request, response);

    }
}
