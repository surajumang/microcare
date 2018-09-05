package com.care.controller.seeker;

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
import java.util.logging.Logger;

public class CloseJob extends HttpServlet {

    private Logger logger = Logger.getLogger("CLoseJob");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/ErrorPage.jsp";

        //make it more robust.Job to be closed.
        int jobToBeClosed = CommonUtil.getJobIdFromRequest(request);
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        logger.info("Called CloseApplication" + currentMember);

        int status = seekerService.closeJob(currentMember, jobToBeClosed);
        if (status == 1){
            page = "/seeker/ShowMyJobs.do";
        }

        logger.info(page);

        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
