package com.care.controller.seeker;

import com.care.beans.Member;
import com.care.dto.form.RegistrationFormDTO;
import com.care.dto.form.SeekerRegistrationDTO;
import com.care.dto.form.SitterRegistrationDTO;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;
import com.care.validation.FormValidator;
import sun.rmi.runtime.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Registration extends HttpServlet {
    Logger logger = Logger.getLogger("SeekerRegistration");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FormBean reg = FormPopulator.populate(request, SeekerRegistrationDTO.class);
        logger.info(reg + " " );
        Map<String, String> errors = new HashMap<String, String>();

        reg.validateCustom(errors);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        logger.info(errors + " ");

        if(errors.isEmpty()){
            RegistrationFormDTO registrationFormDTO = (RegistrationFormDTO)reg;

            logger.info("Without errors");
            logger.info(registrationFormDTO.getMemberType());
            accountService.enroll(registrationFormDTO);
            logger.info("Back at servlet");
        }
        request.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher("/visitor/SeekerRegistration.jsp").forward(request,response);

    }
}
