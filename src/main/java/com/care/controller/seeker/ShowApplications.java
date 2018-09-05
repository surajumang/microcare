package com.care.controller.seeker;

import com.care.model.Application;
import com.care.model.Member;
import com.care.controller.CommonUtil;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ShowApplications extends HttpServlet {
    Logger logger = Logger.getLogger("ShowApplicationsSeeker");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/ErrorPage.jsp";
        int jobIdToViewApplications = CommonUtil.getJobIdFromRequest(request);

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentMember");

        logger.info("Called SeekerService listAppOnJob");
        List<Application> applications =
                seekerService.getApplications(currentMember, jobIdToViewApplications);

        // this has to changed to Collections.emptyList().
        if (applications != null){
            page = "/seeker/ViewApplications.jsp";
            request.setAttribute("getApplications", applications);
        }
        logger.info(page);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
