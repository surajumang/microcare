package com.care.controller.seeker;

import com.care.model.Job;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class ShowJobToEdit extends HttpServlet {

    private Logger logger = Logger.getLogger("ShowJobsToEdit");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Get parameter from the request to refer to the job to be edited.
         */
        int id = -1;
        if (request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
            logger.info(id + " ");
        }

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Job job = seekerService.getJob(id);
        logger.info(job + "-- >>> job here********************************");
        request.setAttribute("editJob", job);

        getServletContext().getRequestDispatcher("/seeker/ShowAndEditJob.jsp").forward(request, response);

    }
}
