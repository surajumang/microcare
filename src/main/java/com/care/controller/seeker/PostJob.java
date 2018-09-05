package com.care.controller.seeker;


import com.care.dto.form.JobDTO;
import com.care.model.Member;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Check if user is logged in[Utility method]
        Set error parameter to be used by the Validator.
         */
        String page ="/ErrorPage.jsp";
        FormBean postJobForm = FormPopulator.populate(request, JobDTO.class);
        Member currentUser = (Member) request.getSession().getAttribute("currentUser");

        Map<String, String> errors = new HashMap<String, String>();
        postJobForm.validateCustom(errors);
        request.setAttribute("errors", errors);

        if(!errors.isEmpty()){
            logger.info("Dispatch with errors.");
            getServletContext().getRequestDispatcher("/seeker/PostJob.jsp").forward(request, response);
            logger.info("callback");
            return;
        }
        JobDTO jobDTO = (JobDTO)postJobForm;
        jobDTO.setSeekerId(String.valueOf(currentUser.getId()));
        
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        int status = seekerService.postJob(currentUser, jobDTO);

        if (status >= 0){
            page = "/seeker/Home.jsp";
        }

        getServletContext().getRequestDispatcher(page).forward(request, response);

    }
}
