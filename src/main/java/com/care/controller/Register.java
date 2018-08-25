package com.care.controller;

import com.care.form.RegistrationForm;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        getServletContext().setAttribute("member",rf);
        System.err.print(rf);
        RequestDispatcher rd = req.getRequestDispatcher("SuccessMessage.jsp");
        rd.forward(req, resp);
    }
}