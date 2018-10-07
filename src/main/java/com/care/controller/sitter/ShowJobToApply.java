package com.care.controller.sitter;

import com.care.controller.CommonUtil;
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
import java.util.logging.Logger;

public class ShowJobToApply extends Action {
    private Logger logger = Logger.getLogger("ShowJobToApply");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        Fetch the job from the database and show it to the user, also collect the expectedPay.
         */
        String page = "failure";
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        OperationStatus operationStatus = OperationStatus.FAILURE;

        /*
        [todo Fetch a Job Attach it to Session. Fill an application form [jobId, sitterId] ]
        Not needed as it is being handled by hibernate.
         */
        try{
            long id = CommonUtil.getIdFromRequest(request, "id" );
            Job job = sitterService.getJob(id);
            // the job must not be CLOSED OR EXPIRED.
            if (job != null && job != Job.emptyJob()){
                operationStatus = OperationStatus.SUCCESS;
                request.getSession().setAttribute("job", job);
                ApplicationForm applicationForm = (ApplicationForm)form;
                applicationForm.setJobId(String.valueOf(job.getId()));
                applicationForm.setSitterId(String.valueOf(member.getId()));
                page = "success";
            }
        }catch (Exception e){
            logger.info("Sitter Show job to Apply ");
            page = "badRequest";
        }
        request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "End");
        return mapping.findForward(page);
    }

    private void createApplication(Job job, ApplicationForm applicationForm){

    }
}
