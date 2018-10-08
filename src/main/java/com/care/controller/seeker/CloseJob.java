package com.care.controller.seeker;

import com.care.exception.BadRequestException;
import com.care.filter.HibernateFilter;
import com.care.model.Member;
import com.care.controller.CommonUtil;
import com.care.controller.ControllerUtil;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseJob extends Action {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "failure";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);

        logger.info("Called CloseApplication  " + currentMember);
        try {
            long jobToBeClosed = CommonUtil.getIdFromRequest(request, "id" );
            //throws exception
            operationStatus = seekerService.closeJob(currentMember, jobToBeClosed);
            if (operationStatus == OperationStatus.SUCCESS){
                request.setAttribute("DELSUCCESS", "Successfully deleted");
                page = "success";
                request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "True");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Job not deleted", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //It is required to throw the caught exception, if operations are to be rolled back.
            throw new BadRequestException(e);
        }
        logger.info(page);
        return mapping.findForward(page);
    }
}
