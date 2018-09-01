package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.dto.form.ApplicationDTO;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowApplications extends HttpServlet {
    Logger logger = Logger.getLogger("ShowApplicationsSeeker");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/ErrorPage.jsp";

        //make it more robust.
        logger.info("-------- " + request.getSession().getAttribute("id").toString() + " -------");

        int jobIdToViewApplications = (Integer)request.getSession().getAttribute("id");;

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        Member currentMember = (Member) request.getSession().getAttribute("currentMember");

        logger.info("Called SeekerService listAppOnJob");

        List<ApplicationDTO> applications =
                seekerService.listApplicationsOnJob(currentMember, jobIdToViewApplications);
        /*
        Need to collect all the applications which are posted on this Job.
        List<ApplicationsFormDTO > SeekerServiceImpl.getApplications(jobId);
        It will delegate the call to ApplicationServiceImpl.getApplications(jobId);
        It will take care of calling ApplicationDAOImpl to fetch actual data.
         */

        if (applications != null){
            page = "/Members/Seeker/ViewApplications.jsp";
        }

        logger.info(page);

        request.setAttribute("applications", applications);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);


    }
}
