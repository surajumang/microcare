package com.care.controller.seeker;

import com.care.exception.IllegalApplicationAccessException;
import com.care.model.Application;
import com.care.model.Member;
import com.care.controller.CommonUtil;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowApplications extends Action {
    Logger logger = Logger.getLogger("ShowApplicationsSeeker");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "No Applications on Job");
        message.put(OperationStatus.SUCCESS, "All applications on this Job");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/seeker/viewApplications.jsp";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        long jobIdToViewApplications = CommonUtil.getIdFromRequest(request, "id" );

        List<Application> applications = Collections.emptyList();

        if (jobIdToViewApplications >= 0){
            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            Member currentMember = (Member) request.getSession().getAttribute("currentUser");

            logger.info("Called SeekerService listAppOnJob");
            try {
                applications = seekerService.getApplications(currentMember, jobIdToViewApplications);
            } catch (IllegalApplicationAccessException e) {
                logger.log(Level.SEVERE, "Not allowed to see application", e);
            }
            if (applications != null && !applications.isEmpty()){
                operationStatus = OperationStatus.SUCCESS;
            }
        }

        request.setAttribute("getApplications", applications);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward("success");
    }
}
