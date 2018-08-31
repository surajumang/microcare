package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.dto.form.ApplicationDTO;
import com.care.exception.IncompatibleUserTypeException;
import com.care.exception.NoUserLoggedInException;
import com.care.service.CommonUtil;
import com.care.service.SeekerService;
import com.care.service.SeekerServiceImpl;
import com.care.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowApplications extends HttpServlet {
    Logger logger = Logger.getLogger("ShowApplicationsSeeker");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/ErrorPage.jsp";
        boolean userLoggedIn = false;
        Member currentMember = null;
        try {
            userLoggedIn = CommonUtil.isMemberLoggedIn(request);
            currentMember = CommonUtil.getLoggedInUserFromSession(request);
        }catch (NoUserLoggedInException e){
            logger.log(Level.SEVERE, "No user logged in", e.getCause());
        }catch (IncompatibleUserTypeException e){
            logger.log(Level.SEVERE, "Error fetching user", e.getCause());
        }

        if(! userLoggedIn || currentMember == null ){
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }
        //make it more robust.
        logger.info("-------- " + request.getSession().getAttribute("id").toString() + " -------");

        int jobIdToViewApplications = (Integer)request.getSession().getAttribute("id");;

        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        List<ApplicationDTO> applications = new ArrayList<ApplicationDTO>();

        applications = seekerService.listApplicationsOnJob(currentMember, jobIdToViewApplications);
        /*
        Need to collect all the applications which are posted on this Job.
        List<ApplicationsFormDTO > SeekerServiceImpl.getApplications(jobId);
        It will delegate the call to ApplicationServiceImpl.getApplications(jobId);
        It will take care of calling ApplicationDAOImpl to fetch actual data.
         */
        request.setAttribute("applications", applications);
        RequestDispatcher rd = request.getRequestDispatcher("/Members/Seeker/ViewApplications.jsp");
        rd.forward(request, response);


    }
}
