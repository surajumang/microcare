package com.care.controller.seeker;

import com.care.exception.BadRequestException;
import com.care.filter.HibernateFilter;
import com.care.model.Job;
import com.care.model.Member;
import com.care.controller.ControllerUtil;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowJobs extends Action {
    private Logger logger = Logger.getLogger("ShowJobs");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/seeker/showMyJobs.jsp";

        Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);

        List<Job> myJobs = null;
        try {
            myJobs = seekerService.listJobs(currentMember);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't show jobs", e);
            throw new BadRequestException(e);
        }
        logger.info(myJobs.size() + "--------");

        request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "End");
        request.setAttribute("myJobs", myJobs);
        logger.info("Dispatching to Page" + page);
        return mapping.findForward("success");
    }
}
