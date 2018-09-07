package com.care.controller.sitter;

import com.care.model.Job;
import com.care.model.Member;
import com.care.service.*;

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

    Logger logger = Logger.getLogger("ShowJobs To Sitter");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "No jobs to Show");
        message.put(OperationStatus.SUCCESS, "All jobs available for you");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/sitter/Home.jsp";

        Member currentMember = (Member) request.getSession().getAttribute("currentUser");
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        OperationStatus operationStatus = OperationStatus.FAILURE;

        List<Job> allJobs = sitterService.listAllAvailableJobs(currentMember);

        logger.info(allJobs.size() + " ");
        if (allJobs != null && !allJobs.isEmpty()){
            page = "/sitter/ShowAllJobs.jsp";
            request.setAttribute("allJobs", allJobs);
            operationStatus = OperationStatus.SUCCESS;
        }
        logger.info(allJobs.toString());
        logger.info("Dispatching to Page" + page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));

        getServletContext().getRequestDispatcher(page).forward(request, response);

    }
}
