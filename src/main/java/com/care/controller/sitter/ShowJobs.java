package com.care.controller.sitter;

import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Sitter;
import com.care.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ShowJobs extends HttpServlet {

    Logger logger = Logger.getLogger("ShowJobs To Sitter");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/ErrorPage.jsp";

        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);

        List<Job> allJobs = sitterService.listAllAvailableJobs(currentMember);

        if (allJobs != null){
            page = "/sitter/ShowAllJobs.jsp";
            request.setAttribute("allJobs", allJobs);
        }
        logger.info(allJobs.toString());
        logger.info("Dispatching to Page" + page);

        getServletContext().getRequestDispatcher(page).forward(request, response);

    }
}
