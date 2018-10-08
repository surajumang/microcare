package com.care.controller.seeker;

import com.care.controller.CommonUtil;
import com.care.exception.BadRequestException;
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

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Member member = (Member)request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Job job = null;
        JobForm jobForm = (JobForm)form;
        String page ="success";
        try {
            long id = CommonUtil.getIdFromRequest(request, "id");
            job = seekerService.getJob(member, id);
            mapJob(job, jobForm);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't Edit an expired job", e);
            throw new BadRequestException(e);
        }
        logger.info("Dispatching to ---" + page);
        return mapping.findForward(page);
    }

    private void mapJob(Job job, JobForm jobForm){
        ObjectMapper.mapObject(job, jobForm, false);

        // Change the fields to proper format after retrieving from DB.
        // split will be a good choice here.
        String[] endDate = jobForm.getEndDateTime().split(" ");

        String endTime= endDate[1].substring(0, 5);

        String[] startDate = jobForm.getStartDateTime().split(" ");

        String startTime= startDate[1].substring(0, 5);

        jobForm.setEndDate(endDate[0]);
        jobForm.setStartDate(startDate[0]);
        jobForm.setStartTime(startTime);
        jobForm.setEndTime(endTime);

//        jobForm.setHourlyPay(String.valueOf(job.getHourlyPay()));
//        jobForm.setSeekerId(String.valueOf(job.getSeekerId()));
//        jobForm.setTitle(job.getTitle());
//        jobForm.setId(String.valueOf(job.getId()));
    }
}
