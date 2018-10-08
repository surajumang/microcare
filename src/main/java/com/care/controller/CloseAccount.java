package com.care.controller;

import com.care.exception.BadRequestException;
import com.care.filter.HibernateFilter;
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

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        First Sets the member status to inactive and then dispatches the rquest to Logout servlet.
         */
        Member currentUser = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        String page = "/member/home.do";

        logger.info("Member deleted successfully");
        OperationStatus operationStatus = null;
        try {
            operationStatus = accountService.deleteMember(currentUser);
        } catch (Exception e) {
            throw new BadRequestException(e);
        }
        // take to login page when closed.
        if (operationStatus == OperationStatus.SUCCESS){
            request.getSession().setAttribute(ControllerUtil.CURRENT_USER, null);
            request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "True");
        }
        return mapping.findForward("success");
    }
}
