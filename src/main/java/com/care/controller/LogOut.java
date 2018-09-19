package com.care.controller;

import com.care.model.Member;
import com.care.service.OperationStatus;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LogOut extends Action {
    Logger logger = Logger.getLogger("Log Out");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("called logout");
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        logger.info(member + "to be logged out");
        request.getSession().setAttribute(ControllerUtil.CURRENT_USER, null);
        request.setAttribute("stat", member);

        request.setAttribute(OperationStatus.SUCCESS.name(), "Successfully Logged Out");

        return mapping.findForward("success");

    }
}
