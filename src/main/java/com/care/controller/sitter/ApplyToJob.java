package com.care.controller.sitter;

import com.care.dto.form.ApplicationDTO;
import com.care.model.Member;
import com.care.service.*;
import com.care.validation.FormPopulator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApplyToJob extends HttpServlet {
    private Logger logger = Logger.getLogger("ApplyToJob");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/sitter/ShowJobToApply.jsp";

        //make it more robust.Job to apply on.
        String jobToApplyOn = request.getParameter("id");
        ApplicationDTO application = FormPopulator.populate(request, ApplicationDTO.class);
        Map<String, String> errors = new HashMap<>();
        int status = -1;
        application.validateCustom(errors);

        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        application.setJobId(jobToApplyOn);
        application.setSitterId(String.valueOf(currentMember.getId()));

        logger.info(application + "**********");
        logger.info(errors + " ");
        logger.info("Called ApplyToJob");

        if (errors.isEmpty()){
            status = sitterService.applyToJob(application);
            logger.info("Status okay");
            page = "/sitter/ShowMyApplications.do";
        }
        if (status == 1){
            logger.info("Dispatching ");
            // send the request to another servlet which will take it to apprropriate place.
            page = "/sitter/ShowMyApplications.do";
        }
        logger.info(page);
        request.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
