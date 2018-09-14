package com.care.controller;

import com.care.model.Member;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
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

public class CloseAccount extends Action {
    Logger logger = Logger.getLogger("CloseAccount");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Couldn't Close");
        message.put(OperationStatus.SUCCESS, "Successfully Closed Account");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        First Sets the member status to inactive and then dispatches the rquest to Logout servlet.
         */
        Member currentUser = (Member) request.getSession().getAttribute("currentUser");
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        String page = "/member/home.do";

        logger.info("Member deleted successfully");
        OperationStatus operationStatus = accountService.deleteMember(currentUser);
        // take to login page when closed.
        if (operationStatus == OperationStatus.SUCCESS){
            page="/login.jsp";
            request.getSession().invalidate();
        }
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward("success");
    }
}
