package com.care.controller.seeker;


import com.care.form.JobForm;
import com.care.model.Member;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PostJob extends Action {
    Logger logger = Logger.getLogger("PostJob");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Can't Post Job");
        message.put(OperationStatus.SUCCESS, "Job Posted successfully");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page ="/seeker/postJob.jsp";
        FormBean postJobForm = FormPopulator.populate(request, JobForm.class);
        Member currentUser = (Member) request.getSession().getAttribute("currentUser");

        JobForm jobForm = (JobForm)postJobForm;
        int status = -1;
        OperationStatus operationStatus = OperationStatus.FAILURE;
        request.setAttribute("formErrors", jobForm);

        logger.info(jobForm + " " );
        logger.info("Without errors");
        jobForm.setSeekerId(String.valueOf(currentUser.getId()));

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        operationStatus = seekerService.postJob(currentUser, jobForm);
        logger.info("Returned with status"  + operationStatus);

        if (operationStatus == OperationStatus.SUCCESS){
            page = "/seeker/home.jsp";
        }
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(page);

    }
}
