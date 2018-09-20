package com.care.controller.sitter;

import com.care.filter.HibernateFilter;
import com.care.model.Member;
import com.care.controller.CommonUtil;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseApplication extends Action {
    Logger logger = Logger.getLogger("CloseJobSitter");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Can't Edit Job");
        message.put(OperationStatus.SUCCESS, "Successfully Deleted");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "failure";
        OperationStatus operationStatus = OperationStatus.FAILURE;



        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);

        logger.info("Called CloseApplication for Sitter" + currentMember);
        try {
            long applicationToBeClosed = CommonUtil.getIdFromRequest(request, "id");
            operationStatus = sitterService.deleteApplication(currentMember, applicationToBeClosed);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't delete", e);
        }

        if (operationStatus == OperationStatus.SUCCESS){
            page = "success";
            request.setAttribute("DELSUCCESS", "Successfully deleted");
            request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG,"True");
        }
        logger.info(page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward("success");
    }
}
