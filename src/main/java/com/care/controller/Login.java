
package com.care.controller;

import com.care.form.LoginDetails;
import com.care.form.RegistrationForm;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormBean ld =  FormPopulator.populate(req, LoginDetails.class);
        // fetch member from application context and compare the password.
        RegistrationForm rf = (RegistrationForm) getServletContext().getAttribute("member");
        System.err.println(rf);
        System.err.println(ld);
        getServletContext().setAttribute("login", ld);
        boolean isUserAvailable = false;

        if(rf != null && ld != null){
            LoginDetails lld = (LoginDetails)ld;
            if(lld.getEmail().equals(rf.getEmail()))
                isUserAvailable = lld.getPassword().equals(rf.getPassword());
        }
        String page;
        if( isUserAvailable){
            page = "jsp/Members/SeekerHome.jsp";
        }
        else {
            page = "jsp/Members/ErrorPage.jsp";
        }
        RequestDispatcher rd = req.getRequestDispatcher(page);
        rd.forward(req, resp);
    }
}
