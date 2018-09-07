package com.care.controller.sitter;

import com.care.model.Application;
import com.care.model.Member;
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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShowApplications extends HttpServlet {

    private Logger logger = Logger.getLogger("ShowApplicationsSitter");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "No applications to show");
        message.put(OperationStatus.SUCCESS, "All applications");
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
        List<Application> allMyApplications = sitterService.listAllApplications(currentMember);

        if (allMyApplications != null && !allMyApplications.isEmpty()){
            page = "/sitter/ShowMyApplications.jsp";
            operationStatus = OperationStatus.SUCCESS;
            request.setAttribute("allMyApplications", allMyApplications);
        }
        logger.info(allMyApplications.toString());
        logger.info("Dispatching to Page" + page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
