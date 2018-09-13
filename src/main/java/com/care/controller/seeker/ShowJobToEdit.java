package com.care.controller.seeker;

import com.care.controller.CommonUtil;
import com.care.exception.JobNotPostedByUserException;
import com.care.form.JobForm;
import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Status;
import com.care.service.*;
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

public class ShowJobToEdit extends Action {

    private Logger logger = Logger.getLogger("ShowJobsToEdit");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, " You Can't Edit this Job");
        message.put(OperationStatus.SUCCESS, "");
        message.put(OperationStatus.INVALID, "Invalid Job ");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        Get parameter from the request to refer to the job to be edited.
         */
        String page = "/member/home.do";

        OperationStatus operationStatus = OperationStatus.FAILURE;
        long id = CommonUtil.getIdFromRequest(request, "id");

        if (id >= 0){
            logger.info("Editing job with id " + id );
            Member member = (Member)request.getSession().getAttribute("currentUser");
            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            Job job = null;
            JobForm jobForm = new JobForm();
            try {
                job = seekerService.getJob(member, id);
                //ObjectMapper.mapObject();
                if (job.getStatus() != Status.EXPIRED && job.getStatus() != Status.CLOSED){
                    operationStatus = OperationStatus.SUCCESS;
                    page = "/seeker/showAndEditJob.jsp";
                    request.setAttribute("editJob", job);
                }
            } catch (JobNotPostedByUserException e) {
                logger.log(Level.SEVERE, "Can't Edit an expired job", e);
            }
            logger.info(job + "-- >>> job here********************************");
        }
        logger.info("Dispatching to ---" + page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));

        return new ActionForward(page);

    }
}
