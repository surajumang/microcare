package com.care.controller.sitter;

import com.care.beans.Application;
import com.care.beans.Member;
import com.care.service.ServiceFactory;
import com.care.service.SitterService;
import com.care.service.SitterServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ShowApplications extends HttpServlet {

    private Logger logger = Logger.getLogger("ShowApplicationsSitter");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/ErrorPage.jsp";

        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);

        List<Application> allMyApplications = sitterService.listAllApplications(currentMember, currentMember.getId());

        if (allMyApplications != null){
            page = "/Sitter/ShowAllJobs.jsp";
            request.setAttribute("allMyApplications", allMyApplications);
        }
        logger.info(allMyApplications.toString());
        logger.info("Dispatching to Page" + page);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
