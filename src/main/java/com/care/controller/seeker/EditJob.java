package com.care.controller.seeker;

import com.care.dto.form.JobDTO;
import com.care.model.Member;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.Service;
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

public class EditJob extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = "/seeker/ShowAndEditJob.jsp";
        JobDTO jobForm = FormPopulator.populate(req, JobDTO.class);
        Map<String, String> errors = new HashMap<>();
        jobForm.validateCustom(errors);

        Member currentUser = (Member)req.getSession().getAttribute("currentUser");

        if(errors.isEmpty()){
            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            seekerService.editJob(currentUser, jobForm);
            page = "/seeker/Home.jsp";
        }
        req.setAttribute("editJob", jobForm);
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
