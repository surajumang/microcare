package com.care.controller.seeker;

import com.care.exception.JobNotPostedByUserException;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseJob extends Action {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final Map<OperationStatus, String> messege = new HashMap<OperationStatus, String>();
    static {
        messege.put(OperationStatus.SUCCESS, "Successfully Closed");
        messege.put(OperationStatus.FAILURE, "No records exist");
        messege.put(OperationStatus.UNAUTHORISED, "You are not authorised to perform this Operation ");
        messege.put(OperationStatus.INVALID, "Invalid JobId");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/seeker/showJobs.do";
        OperationStatus operationStatus = OperationStatus.FAILURE;

        long jobToBeClosed = CommonUtil.getIdFromRequest(request, "id" );
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        if (jobToBeClosed >= 0){
            logger.info("Called CloseApplication  " + currentMember);
            try {
                operationStatus = seekerService.closeJob(currentMember, jobToBeClosed);
                if (operationStatus == OperationStatus.SUCCESS){
                    request.setAttribute("DELSUCCESS", "Successfully deleted");
                }
            } catch (JobNotPostedByUserException e) {
                logger.log(Level.SEVERE, "Job not deleted", e);
                //[TODO]replace it with bad request.
                operationStatus = OperationStatus.UNAUTHORISED;
            }
        }

        request.setAttribute(operationStatus.name(), messege.get(operationStatus));
        logger.info(page);
        return mapping.findForward("success");
    }
}
