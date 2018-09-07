package com.care.controller.seeker;


import com.care.dto.form.JobDTO;
import com.care.model.Member;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;
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

public class PostJob extends HttpServlet {
    Logger logger = Logger.getLogger("PostJob");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Can't Post Job");
        message.put(OperationStatus.SUCCESS, "Job Posted successfully");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page ="/seeker/PostJob.jsp";
        FormBean postJobForm = FormPopulator.populate(request, JobDTO.class);
        Member currentUser = (Member) request.getSession().getAttribute("currentUser");

        Map<String, String> errors = new HashMap<String, String>();
        postJobForm.validateCustom(errors);
        JobDTO jobDTO = (JobDTO)postJobForm;
        int status = -1;
        OperationStatus operationStatus = OperationStatus.FAILURE;

        request.setAttribute("errors", errors);
        request.setAttribute("formErrors", jobDTO);
        logger.info(errors + " ");
        logger.info(jobDTO + " " );
        if(errors.isEmpty()){
            logger.info("Without errors");
            jobDTO.setSeekerId(String.valueOf(currentUser.getId()));

            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            operationStatus = seekerService.postJob(currentUser, jobDTO);
            logger.info("Returned with status"  + operationStatus);

            if (operationStatus == OperationStatus.SUCCESS){
                page = "/seeker/Home.jsp";
            }
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(request, response);

    }
}
