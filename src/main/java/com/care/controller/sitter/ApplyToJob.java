package com.care.controller.sitter;

import com.care.controller.CommonUtil;
import com.care.form.ApplicationForm;
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
        message.put(OperationStatus.FAILURE, "Unable to Apply");
        message.put(OperationStatus.SUCCESS, "");
        message.put(OperationStatus.INVALID, "Invalid jobID");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/sitter/ShowJobToApply.jsp";

        long jobToApplyOn = CommonUtil.getIdFromRequest(request, "id" );
        ApplicationForm application = FormPopulator.populate(request, ApplicationForm.class);

        Map<String, String> errors = new HashMap<>();
        OperationStatus operationStatus = OperationStatus.FAILURE;
        application.validateCustom(errors);

        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        logger.info(application + "**********");
        logger.info(errors + " ");
        logger.info("Called ApplyToJob");

        if (jobToApplyOn >=0 && errors.isEmpty() ){
            application.setJobId(String.valueOf(jobToApplyOn));
            application.setSitterId(String.valueOf(currentMember.getId()));

            operationStatus = sitterService.applyToJob(application);
            logger.info("Status okay " + operationStatus);

            if (operationStatus == OperationStatus.SUCCESS){
                page = "/sitter/ShowAllJobs.do";
                request.setAttribute("APPSUCCESS", "Applied successfully");
            }
        }


        logger.info(page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        request.setAttribute("application", application);
        request.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher(page).include(request, response);
    }
}
