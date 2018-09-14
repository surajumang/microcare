package com.care.controller.sitter;

import com.care.model.Application;
import com.care.model.Member;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import com.care.service.SitterService;
import com.care.service.SitterServiceImpl;
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

public class ShowApplications extends Action {
    private Logger logger = Logger.getLogger("ShowApplicationsSitter");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "No applications to show");
        message.put(OperationStatus.SUCCESS, "");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "failure";

        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        OperationStatus operationStatus = OperationStatus.FAILURE;
        List<Application> allMyApplications = sitterService.listAllApplications(currentMember);

        if (allMyApplications != null && !allMyApplications.isEmpty()){
            page = "success";
            operationStatus = OperationStatus.SUCCESS;
            request.setAttribute("allMyApplications", allMyApplications);
        }
        logger.info(allMyApplications.toString());
        logger.info("Dispatching to Page" + page);

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward("success");
    }
}
