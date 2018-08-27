package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.dto.AppliedJob;
import com.care.dto.form.Job;
import com.care.service.SeekerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowJobs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession();
        Member member = (Member)currentSession.getAttribute("currentUser");
        String page = "jsp/ErrorPage.jsp";

        if(member == null){
            RequestDispatcher rd = req.getRequestDispatcher(page);
            rd.forward(req, resp);
        }
        List<AppliedJob> myJobs = new ArrayList<AppliedJob>();

        /*
        Db call to fetch the jobs created by this user.
        List<Job> lj = SeekerServiceImpl.getJobs(member);
        SeekerService will simply delegate this call to JobServiceImpl(member.getId()) -> Job form object.
        This will further be delegated to JobDAO which will give a model class Job.
         */
        req.setAttribute("myJobs", myJobs);
        RequestDispatcher rd = req.getRequestDispatcher("ShowJobs.jsp");
        rd.forward(req, resp);

    }
}
