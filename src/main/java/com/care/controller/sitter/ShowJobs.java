package com.care.controller.sitter;

import com.care.filter.HibernateFilter;
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

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "success";
        Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);

        List<Job> allJobs = sitterService.listAllAvailableJobs(currentMember);

        request.setAttribute("allJobs", allJobs);
        logger.info(allJobs.toString());
        logger.info("Dispatching to Page" + page);
        request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "End");
        return mapping.findForward("success");
    }
}
