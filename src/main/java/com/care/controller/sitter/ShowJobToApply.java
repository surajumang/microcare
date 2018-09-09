package com.care.controller.sitter;

import com.care.controller.CommonUtil;
import com.care.model.Job;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import com.care.service.SitterService;
import com.care.service.SitterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ShowJobToApply extends HttpServlet {
    private Logger logger = Logger.getLogger("ShowJobToApply");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Unable to Get Job for Application");
        message.put(OperationStatus.SUCCESS, "Apply to Job");
        message.put(OperationStatus.INVALID, "Invalid jobID");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Fetch the job from the database and show it to the user, also collect the expectedPay.
         */
        String page = "/sitter/ShowAllJobs.do";
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        OperationStatus operationStatus = OperationStatus.FAILURE;
        long id = CommonUtil.getIdFromRequest(request, "id" );

        if (id >= 0){
            Job job = sitterService.getJob(id);
            if (job != null && job != Job.EMPTY_JOB){
                page = "/sitter/ShowJobToApply.jsp";
                operationStatus = OperationStatus.SUCCESS;
                request.getSession().setAttribute("job", job);
            }
        }
        request.setAttribute(operationStatus.name(), message.get(operationStatus) );
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
