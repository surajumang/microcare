package com.care.controller;

import com.care.form.RegistrationForm;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;
import com.care.validation.FormValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        Read the form parameters and save it in application context.
         */
        FormBean rf = FormPopulator.populate(req, RegistrationForm.class);
        Map<String, String> m = new HashMap<String, String>();


        req.setAttribute("errors", m);
        FormValidator.validate(rf, req);

        getServletContext().setAttribute("member",rf);
        req.setAttribute("user", ((RegistrationForm)rf).getEmail());
        System.err.print(rf);

        System.err.println(m);
        //req.setAttribute("errors", m);

        if(! m.isEmpty()){
            RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
            rd.forward(req, resp);
        }

        RequestDispatcher rd = req.getRequestDispatcher("SuccessMessage.jsp");
        rd.forward(req, resp);
    }
}