package com.care.controller.sitter;

import com.care.model.Job;
import com.care.service.ServiceFactory;
import com.care.service.SitterService;
import com.care.service.SitterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowJobToApply extends HttpServlet {
    private Logger logger = Logger.getLogger("ShowJobToApply");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Fetch the job from the database and show it to the user, also collect the expectedPay.
         */
        String page = "/ErrorPage.jsp";
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        int id = -1;
        try{
            id = Integer.parseInt(request.getParameter("id"));
        }catch (IllegalArgumentException e){
            logger.log(Level.SEVERE, "Invalid ID", e);
        }
        if (id >= 0){
            Job job = sitterService.getJob(id);
            if (job != Job.EMPTY_JOB){
                page = "/sitter/ShowJobToApply.jsp";
                request.getSession().setAttribute("job", job);
            }
        }
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
