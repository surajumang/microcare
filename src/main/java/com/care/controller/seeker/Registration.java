package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.dto.form.SeekerRegistration;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;
import com.care.validation.FormValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        Member member = (Member)currentSession.getAttribute("currentUser");
        if(member != null){
            /*
            There is already a logged in user.
             */
            RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
            rd.forward(req, resp);
        }

        FormBean rf = FormPopulator.populate(req, SeekerRegistration.class);
        Map<String, String> m = new HashMap<String, String>();

        req.setAttribute("errors", m);
        System.err.print(rf);
        FormValidator.validate(rf, req);


        if(currentSession.isNew()){
            req.setAttribute("user", ((SeekerRegistration)rf).getEmail());
        }

        System.err.println(m);
        if(! m.isEmpty()){

            RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
            rd.forward(req, resp);
        }
      //  SeekerServiceImpl.registerMember((SeekerRegistration)rf);
        RequestDispatcher rd = req.getRequestDispatcher("SuccessMessage.jsp");
        rd.forward(req, resp);
    }
}
