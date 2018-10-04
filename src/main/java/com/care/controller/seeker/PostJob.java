package com.care.controller.seeker;


import com.care.form.JobForm;
import com.care.model.Member;
import com.care.controller.ControllerUtil;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PostJob extends Action {
    Logger logger = Logger.getLogger("PostJob");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page ="/seeker/postJob.jsp";

        Member currentUser = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        JobForm jobForm = (JobForm)form;
        int status = -1;
        OperationStatus operationStatus = OperationStatus.FAILURE;
        request.setAttribute("formErrors", jobForm);

        logger.info(jobForm + " " );
        logger.info("Without errors");
        jobForm.setSeekerId(String.valueOf(currentUser.getId()));

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        operationStatus = seekerService.postJob(currentUser, jobForm);
        logger.info("Returned with status"  + operationStatus);

        if (operationStatus == OperationStatus.SUCCESS){
            page = "/member/home.do";
        }

        return mapping.findForward("success");

    }
}
