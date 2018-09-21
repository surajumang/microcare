package com.care.controller.seeker;

import com.care.exception.InvalidApplicationException;
import com.care.model.Application;
import com.care.model.Member;
import com.care.controller.ControllerUtil;
import com.care.controller.CommonUtil;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowApplications extends Action {
    Logger logger = Logger.getLogger("ShowApplicationsSeeker");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "No Applications on Job");
        message.put(OperationStatus.SUCCESS, "All applications on this Job");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "success";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        List<Application> applications = Collections.emptyList();

        try{
            long jobIdToViewApplications = CommonUtil.getIdFromRequest(request, "id" );
            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);

            logger.info("Called SeekerService listAppOnJob");
            //Applications can be seen only if the Job is Active.
            applications = seekerService.getApplications(currentMember, jobIdToViewApplications);
            if (applications != null && !applications.isEmpty()){
                operationStatus = OperationStatus.SUCCESS;
                page = "success";
            }
        }catch (Exception e){
            //redirect to global forward config which will send it to
            logger.log(Level.SEVERE, "Not allowed to see application", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            page="badRequest";
        }
        request.setAttribute("getApplications", applications);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(page);
    }
}
