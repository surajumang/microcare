package com.care.controller.sitter;

import com.care.controller.ControllerUtil;
import com.care.filter.HibernateFilter;
import com.care.form.ApplicationForm;
import com.care.model.Application;
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
        String page = "failure";
        /*
        JobID not required from scope now. It can be fetched from the applicationForm.
         */
        //long jobToApplyOn = CommonUtil.getIdFromRequest(request, "id" );

        ApplicationForm applicationForm = (ApplicationForm)form;
        long jobToApplyOn = Long.valueOf(applicationForm.getJobId());

        OperationStatus operationStatus = OperationStatus.FAILURE;

        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);

        logger.info(applicationForm + "**********");
        logger.info("Called ApplyToJob");

        if (jobToApplyOn >=0 ){
//            applicationForm.setJobId(String.valueOf(jobToApplyOn));
//            applicationForm.setSitterId(String.valueOf(currentMember.getId()));

            operationStatus = sitterService.applyToJob(applicationForm);
            logger.info("Status okay " + operationStatus);

            if (operationStatus == OperationStatus.SUCCESS){
                page = "success";
                request.setAttribute("APPSUCCESS", "Applied successfully");
                request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG,"True");
            }
        }

        logger.info(page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        request.setAttribute("applicationForm", applicationForm);
        return mapping.findForward("success");
    }
}
