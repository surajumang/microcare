package com.care.controller.sitter;

import com.care.model.Member;
import com.care.controller.CommonUtil;
import com.care.model.Sitter;
import com.care.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CloseApplication extends HttpServlet {
    Logger logger = Logger.getLogger("CloseJobSitter");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Can't Edit Job");
        message.put(OperationStatus.SUCCESS, "Edit Successful");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/sitter/Home.jsp";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        int applicationToBeClosed = CommonUtil.getJobIdFromRequest(request, operationStatus);


        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        logger.info("Called CloseApplication for Sitter" + currentMember);

        operationStatus = sitterService.deleteApplication(currentMember, applicationToBeClosed);

        if (operationStatus == OperationStatus.SUCCESS){
            page = "/sitter/ShowMyApplications.do";
        }
        logger.info(page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
