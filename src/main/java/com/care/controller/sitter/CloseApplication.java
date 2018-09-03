package com.care.controller.sitter;

import com.care.beans.Member;
import com.care.controller.CommonUtil;
import com.care.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseApplication extends HttpServlet {
    Logger logger = Logger.getLogger("CloseJobSitter");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/member/ErrorPage.jsp";
        int applicationToBeClosed = CommonUtil.getJobIdFromRequest(request);


        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentUser");

        logger.info("Called CloseApplication for Sitter" + currentMember);

        int status = sitterService.deleteApplication(currentMember, applicationToBeClosed);

        if (status == 1){
            page = "/member/sitter/ShowMyApplications.do";
        }
        logger.info(page);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
