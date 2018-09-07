package com.care.controller.seeker;

import com.care.dto.form.JobDTO;
import com.care.model.Member;
import com.care.service.*;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class EditJob extends HttpServlet {
    private Logger logger = Logger.getLogger("EditJob");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Can't Edit this Job");
        message.put(OperationStatus.SUCCESS, "Edit Successful");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = "/seeker/ShowAndEditJob.jsp";
        JobDTO jobForm = FormPopulator.populate(req, JobDTO.class);
        Map<String, String> errors = new HashMap<>();
        jobForm.validateCustom(errors);
        OperationStatus operationStatus = OperationStatus.FAILURE;

        Member currentUser = (Member)req.getSession().getAttribute("currentUser");
        logger.info(errors + "ERRORS");

        if(errors.isEmpty()){
            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);

            operationStatus = seekerService.editJob(currentUser, jobForm);
            logger.info("job status is " + operationStatus);
            page = "/seeker/Home.jsp";
        }
        req.setAttribute("editJob", jobForm);
        req.setAttribute("errors", errors);
        req.setAttribute(operationStatus.name(), message.get(operationStatus));

        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
