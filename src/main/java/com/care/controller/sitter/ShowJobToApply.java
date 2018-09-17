package com.care.controller.sitter;

import com.care.controller.CommonUtil;
import com.care.form.ApplicationForm;
import com.care.model.Job;
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
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Unable to Get Job for Application");
        message.put(OperationStatus.SUCCESS, "Apply to Job");
        message.put(OperationStatus.INVALID, "Invalid jobID");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        Fetch the job from the database and show it to the user, also collect the expectedPay.
         */
        String page = "/sitter/showAllJobs.do";
        Member member = (Member) request.getSession().getAttribute("currentUser");
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        OperationStatus operationStatus = OperationStatus.FAILURE;
        long id = CommonUtil.getIdFromRequest(request, "id" );
        /*
        [todo Fetch a Job Attach it to Session. Fill an application form [jobId, sitterId] ]
         */
        if (id >= 0){
            Job job = sitterService.getJob(id);
            if (job != null && job != Job.EMPTY_JOB){
                page = "/sitter/showJobToApply.jsp";
                operationStatus = OperationStatus.SUCCESS;
                request.getSession().setAttribute("job", job);

                ApplicationForm applicationForm = (ApplicationForm)form;
                applicationForm.setJobId(String.valueOf(job.getId()));

                applicationForm.setSitterId(String.valueOf(member.getId()));
            }
        }
        request.setAttribute(operationStatus.name(), message.get(operationStatus) );

        return mapping.findForward("success");
    }

    private void createApplication(Job job, ApplicationForm applicationForm){

    }
}
