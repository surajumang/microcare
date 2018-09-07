package com.care.controller.seeker;

import com.care.exception.JobNotPostedByUserException;
import com.care.model.Member;
import com.care.controller.CommonUtil;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseJob extends HttpServlet {

    private static final Map<OperationStatus, String> messege = new HashMap<OperationStatus, String>();

    static {
        messege.put(OperationStatus.SUCCESS, "Successfully Closed");
        messege.put(OperationStatus.FAILURE, "No records exist");
        messege.put(OperationStatus.UNAUTHORISED, "You are not authorised to perform this Operation ");
        messege.put(OperationStatus.INVALID, "Invalid JobId");
    }

    private Logger logger = Logger.getLogger("CLoseJob");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/seeker/ShowMyJobs.do";
        OperationStatus operationStatus = OperationStatus.FAILURE;

        int jobToBeClosed = CommonUtil.getJobIdFromRequest(request);

        if (jobToBeClosed < 0){
            operationStatus = OperationStatus.INVALID;
            request.setAttribute(operationStatus.name(), messege.get(operationStatus));
            getServletContext().getRequestDispatcher(page).forward(request, response);
            return;
        }
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        logger.info("Called CloseApplication  " + currentMember);
        try {
            operationStatus = seekerService.closeJob(currentMember, jobToBeClosed);
        } catch (JobNotPostedByUserException e) {
            logger.log(Level.SEVERE, "Job not pos", e);
            //replace it with bad request.
            operationStatus = OperationStatus.UNAUTHORISED;
        }
        request.setAttribute(operationStatus.name(), messege.get(operationStatus));

        logger.info(page);

        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
