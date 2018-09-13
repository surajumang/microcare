package com.care.controller.seeker;

import com.care.form.JobForm;
import com.care.model.Member;
import com.care.service.*;
import com.care.validation.FormPopulator;
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
import java.util.logging.Logger;

public class EditJob extends Action {
    private Logger logger = Logger.getLogger("EditJob");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Can't Edit this Job");
        message.put(OperationStatus.SUCCESS, "Edit Successful");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Member currentUser = (Member) request.getSession().getAttribute("currentUser");
        String page = "/seeker/showAndEditJob.jsp";
        JobForm jobForm = (JobForm)form;
        jobForm.setSeekerId(String.valueOf(currentUser.getId()));

        OperationStatus operationStatus = OperationStatus.FAILURE;

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);

        operationStatus = seekerService.editJob(currentUser, jobForm);
        logger.info("job status is " + operationStatus);
        page = "/seeker/home.jsp";

        request.setAttribute("editJob", jobForm);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));

        return new ActionForward(page);
    }
}
