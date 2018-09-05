package com.care.controller;

import com.care.model.Member;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class CloseAccount extends HttpServlet {
    Logger logger = Logger.getLogger("CloseAccount");
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

        logger.info("Member deleted successfully");
        accountService.deleteMember(currentUser.getId());
        getServletContext().getRequestDispatcher("/member/Logout.do").forward(request, response);

    }
}
