package com.care.controller.sitter;

import com.care.controller.CommonUtil;
import com.care.form.ApplicationForm;
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

public class ApplyToJob extends Action {
    private Logger logger = Logger.getLogger("ApplyToJob");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Unable to Apply");
        message.put(OperationStatus.SUCCESS, "");
        message.put(OperationStatus.INVALID, "Invalid jobID");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/sitter/showJobToApply.jsp";

        long jobToApplyOn = CommonUtil.getIdFromRequest(request, "id" );
        ApplicationForm application = FormPopulator.populate(request, ApplicationForm.class);

        OperationStatus operationStatus = OperationStatus.FAILURE;

        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        logger.info(application + "**********");
        logger.info("Called ApplyToJob");

        if (jobToApplyOn >=0 ){
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
        return mapping.findForward(page);
    }
}
