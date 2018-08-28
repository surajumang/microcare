package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.controller.ControllerUtil;
import com.care.dto.AppliedApplication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowApplictions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = ControllerUtil.getCurrentUser(req);
        if(member == null){
            /*
            Forward it to errorPage.
             */
        }
        List<AppliedApplication> applications = new ArrayList<AppliedApplication>();
        /*
        Need to collect all the applications which are posted on this Job.
        List<ApplicationsFormDTO > SeekerServiceImpl.getApplications(jobId);
        It will delegate the call to ApplicationServiceImpl.getApplications(jobId);
        It will take care of calling ApplicationDAOImpl to fetch actual data.
         */
        req.setAttribute("applications", applications);

    }
}
