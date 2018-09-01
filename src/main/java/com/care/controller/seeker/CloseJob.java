package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.dto.form.ApplicationDTO;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class CloseJob extends HttpServlet {

    private Logger logger = Logger.getLogger("CLoseJob");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/Members/ErrorPage.jsp";

        //make it more robust.Job to be closed.
        logger.info("-------- " + request.getParameter("id") + " -------");

        int jobToBeClosed = Integer.parseInt(request.getParameter("id"));

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        logger.info(currentMember.toString());

        logger.info("Called CloseJob" + currentMember);


        int status = seekerService.closeJob(currentMember, jobToBeClosed);

        if (status == 1){
            page = "/Members/Seeker/ShowMyJobs.jsp";
        }


        logger.info(page);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);


    }
}
