package com.care.controller.seeker;

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
import java.util.logging.Logger;

public class ShowJobs extends Action {
    private Logger logger = Logger.getLogger("ShowJobs");
    private static final Map<OperationStatus, String> messege = new HashMap<OperationStatus, String>();
    static {
        messege.put(OperationStatus.SUCCESS, "");
        messege.put(OperationStatus.FAILURE, "You have not posted any Jobs");
        messege.put(OperationStatus.UNAUTHORISED, "You are not authorised to see the contents");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/seeker/showMyJobs.jsp";

        Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);

        OperationStatus status = OperationStatus.FAILURE;
        List<Job> myJobs = seekerService.listJobs(currentMember);
        logger.info(myJobs.size() + "--------");

        if (myJobs != null && !myJobs.isEmpty()){
            status = OperationStatus.SUCCESS;
        }
        request.setAttribute("myJobs", myJobs);
        request.setAttribute(status.name(), messege.get(status));

        logger.info("Dispatching to Page" + page);
        return mapping.findForward("success");
    }
}
