package com.care.controller.sitter;

import com.care.beans.Job;
import com.care.beans.Member;
import com.care.dto.form.JobDTO;
import com.care.service.*;

import javax.servlet.RequestDispatcher;
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
        String page = "/Members/ErrorPage.jsp";

        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);

        List<Job> allJobs = sitterService.listAllJobs();

        if (allJobs != null){
            page = "/Members/Sitter/ShowAllJobs.jsp";
        }

        request.setAttribute("allJobs", allJobs);
        logger.info(allJobs.toString());
        logger.info("Dispatching to Page" + page);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);

    }
}
