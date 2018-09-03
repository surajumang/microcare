package com.care.controller.sitter;

import com.care.beans.Member;
import com.care.controller.CommonUtil;
import com.care.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplyToJob extends HttpServlet {
    private Logger logger = Logger.getLogger("ApplyToJob");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/member/ErrorPage.jsp";

        //make it more robust.Job to apply on.
        int jobToApplyOn = CommonUtil.getJobIdFromRequest(request);

        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        logger.info(currentMember.toString());
        logger.info("Called ApplyToJob" + currentMember);
        int status = sitterService.applyToJob(currentMember, jobToApplyOn);

        if (status == 1){
            // send the request to another servlet which will take it to apprropriate place.
            page = "/member/seeker/ShowMyJobs.jsp";
        }
        logger.info(page);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
