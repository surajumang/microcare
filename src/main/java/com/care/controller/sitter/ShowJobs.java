package com.care.controller.sitter;

import com.care.model.Job;
import com.care.model.Member;
import com.care.service.*;
import com.care.controller.ControllerUtil;
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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShowJobs extends Action {
    Logger logger = Logger.getLogger("ShowJobs To Sitter");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "No jobs to Show");
        message.put(OperationStatus.SUCCESS, "All jobs available for you");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "failure";

        Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        OperationStatus operationStatus = OperationStatus.FAILURE;

        List<Job> allJobs = sitterService.listAllAvailableJobs(currentMember);

        logger.info(allJobs.size() + " ");
        if (allJobs != null && !allJobs.isEmpty()){
            page = "success";
            request.setAttribute("allJobs", allJobs);
            operationStatus = OperationStatus.SUCCESS;
        }
        logger.info(allJobs.toString());
        logger.info("Dispatching to Page" + page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));

        return mapping.findForward("success");
    }
}
