package com.care.controller;

import com.care.dto.form.RegistrationFormDTO;
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

public class Register extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FormBean rf = FormPopulator.populate(req, RegistrationFormDTO.class);

        /*
        SeekerServiceImpl.enroll((RegistrationFormDTO)rf);
        [TODO]
        */
        getServletContext().getRequestDispatcher("SuccessMessage.jsp").forward(req, resp);
    }
}