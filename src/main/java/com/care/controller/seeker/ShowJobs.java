package com.care.controller.seeker;

import com.care.model.Job;
import com.care.model.Member;
import com.care.service.OperationStatus;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShowJobs extends HttpServlet {
    private static final Map<OperationStatus, String> messege = new HashMap<OperationStatus, String>();

    static {
        messege.put(OperationStatus.SUCCESS, "");
        messege.put(OperationStatus.FAILURE, "You Don't have any Jobs");
        messege.put(OperationStatus.UNAUTHORISED, "You are not authorised to see the contents");
    }

    private Logger logger = Logger.getLogger("ShowJobs");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/seeker/Home.jsp";

        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);

        OperationStatus status = OperationStatus.FAILURE;
        List<Job> myJobs = seekerService.listJobs(currentMember);
        logger.info(myJobs.size() + "--------");

        if (myJobs != null && !myJobs.isEmpty()){
            page = "/seeker/ShowMyJobs.jsp";
            status = OperationStatus.SUCCESS;
            request.setAttribute("myJobs", myJobs);
        }
        request.setAttribute(status.name(), messege.get(status));

        logger.info("Dispatching to Page" + page);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
