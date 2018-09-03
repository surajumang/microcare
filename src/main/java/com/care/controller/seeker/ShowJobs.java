package com.care.controller.seeker;

import com.care.beans.Job;
import com.care.beans.Member;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowJobs extends HttpServlet {

    private Logger logger = Logger.getLogger("ShowJobs");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/member/ErrorPage.jsp";

        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);

        List<Job> myJobs = seekerService.listJobs(currentMember);

        if (myJobs != null){
            page = "/member/seeker/ShowMyJobs.jsp";
            request.setAttribute("myJobs", myJobs);
        }
        logger.info("Dispatching to Page" + page);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
