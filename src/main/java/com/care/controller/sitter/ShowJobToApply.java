package com.care.controller.sitter;

import com.care.controller.CommonUtil;
import com.care.exception.BadRequestException;
import com.care.filter.HibernateFilter;
import com.care.form.ApplicationForm;
import com.care.model.Job;
import com.care.controller.ControllerUtil;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowJobToApply extends Action {
    private Logger logger = Logger.getLogger("ShowJobToApply");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        Fetch the job from the database and show it to the user, also collect the expectedPay.
         */
        String page = "success";
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);

        try{
            long id = CommonUtil.getIdFromRequest(request, "id" );
            Job job = sitterService.getJob(id);
            // the job must not be CLOSED OR EXPIRED.
            // the service will either throw an exception or a proper job Nothing related to EMPty job.
            if (job != null && job != Job.emptyJob()){
                request.getSession().setAttribute("job", job);
                ApplicationForm applicationForm = (ApplicationForm)form;
                applicationForm.setJobId(String.valueOf(job.getId()));
                applicationForm.setSitterId(String.valueOf(member.getId()));
                page = "success";
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Sitter Show job to Apply ", e);
//            page = "badRequest";
            throw new BadRequestException(e);
        }
        request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "End");
        return mapping.findForward(page);
    }
}
