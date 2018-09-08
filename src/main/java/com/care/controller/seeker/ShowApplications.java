package com.care.controller.seeker;

import com.care.exception.IllegalApplicationAccessException;
import com.care.model.Application;
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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowApplications extends HttpServlet {
    Logger logger = Logger.getLogger("ShowApplicationsSeeker");

    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "No Applications on Job");
        message.put(OperationStatus.SUCCESS, "All applications on this Job");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/seeker/Home.jsp";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        int jobIdToViewApplications = CommonUtil.getJobIdFromRequest(request);

        if (jobIdToViewApplications >= 0){
            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            Member currentMember = (Member) request.getSession().getAttribute("currentUser");

            logger.info("Called SeekerService listAppOnJob");
            List<Application> applications = null;
            try {
                applications = seekerService.getApplications(currentMember, jobIdToViewApplications);
            } catch (IllegalApplicationAccessException e) {
                logger.log(Level.SEVERE, "Not allowed to see application");
            }
            if (applications != null && !applications.isEmpty()){
                page = "/seeker/ViewApplications.jsp";
                operationStatus = OperationStatus.SUCCESS;
                request.setAttribute("getApplications", applications);
            }
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
