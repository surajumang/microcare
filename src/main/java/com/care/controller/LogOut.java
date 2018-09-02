package com.care.controller;

import com.care.beans.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LogOut extends HttpServlet {
    Logger logger = Logger.getLogger("Log Out");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("called logout");
        Member member = (Member) request.getSession().getAttribute("currentUser");
        logger.info(member + "to be logged out");
        request.getSession().invalidate();
        request.setAttribute("stat", member);
        getServletContext().getRequestDispatcher("/Logout.jsp").forward(request, response);

    }
}
