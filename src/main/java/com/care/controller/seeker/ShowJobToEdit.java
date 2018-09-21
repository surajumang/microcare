package com.care.controller.seeker;

import com.care.controller.CommonUtil;
import com.care.exception.JobNotPostedByUserException;
import com.care.form.JobForm;
import com.care.model.Job;
import com.care.controller.ControllerUtil;
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

        OperationStatus operationStatus = OperationStatus.FAILURE;
        Member member = (Member)request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Job job = null;
        JobForm jobForm = (JobForm)form;
        String page ="success";
        try {
            long id = CommonUtil.getIdFromRequest(request, "id");
            job = seekerService.getJob(member, id);
            mapJob(job, jobForm);
            //expired applications should also throw exception.
            if (job.getStatus() != Status.EXPIRED && job.getStatus() != Status.CLOSED){
                operationStatus = OperationStatus.SUCCESS;
                //page = "/seeker/showAndEditJob.jsp";
                page = "success";
                //not required anymore[todo]
                request.setAttribute("editJob", jobForm);
            }else {
                //[todo] JobExpiredException.
                throw new Exception();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't Edit an expired job", e);
            //send to global forward [todo]
            page = "badRequest";
        }
        logger.info("Dispatching to ---" + page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(page);
    }

    private void mapJob(Job job, JobForm jobForm){
        ObjectMapper.mapObject(job, jobForm, false);


        String endDate = jobForm.getEndDate();
        endDate = endDate.substring(0, endDate.length() - 5);
        String startDate = jobForm.getStartDate();
        startDate = startDate.substring(0, startDate.length() - 5);

        jobForm.setEndDate(endDate);
        jobForm.setStartDate(startDate);
//        jobForm.setHourlyPay(String.valueOf(job.getHourlyPay()));
//        jobForm.setSeekerId(String.valueOf(job.getSeekerId()));
//        jobForm.setTitle(job.getTitle());
//        jobForm.setId(String.valueOf(job.getId()));
    }
}
