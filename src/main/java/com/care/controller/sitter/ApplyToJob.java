package com.care.controller.sitter;

import com.care.controller.CommonUtil;
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
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Unable to Get Job for Application");
        message.put(OperationStatus.SUCCESS, "Got a job");
        message.put(OperationStatus.INVALID, "Invalid jobID");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/sitter/ShowJobToApply.jsp";

        int jobToApplyOn = CommonUtil.getJobIdFromRequest(request);

        ApplicationDTO application = FormPopulator.populate(request, ApplicationDTO.class);
        Map<String, String> errors = new HashMap<>();
        OperationStatus status=OperationStatus.FAILURE;
        application.validateCustom(errors);

        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        logger.info(application + "**********");
        logger.info(errors + " ");
        logger.info("Called ApplyToJob");

        if (jobToApplyOn >=0 && errors.isEmpty() ){
            application.setJobId(String.valueOf(jobToApplyOn));
            application.setSitterId(String.valueOf(currentMember.getId()));

            status = sitterService.applyToJob(application);
            logger.info("Status okay " + status);

            if (status == OperationStatus.SUCCESS){
                page = "/sitter/ShowMyApplications.do";
            }
        }


        logger.info(page);
        request.setAttribute("application", application);
        request.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
