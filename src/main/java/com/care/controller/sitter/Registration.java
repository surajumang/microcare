package com.care.controller.sitter;

import com.care.dto.form.RegistrationFormDTO;
import com.care.dto.form.SitterRegistrationDTO;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

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
    private Logger logger = Logger.getLogger("SitterRegistration");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //SitterService server = ServerFactory.getInstance("seeker");
        String page = "/ErrorPage.jsp";
        FormBean reg = FormPopulator.populate(request, SitterRegistrationDTO.class);
        logger.info(reg + " " );
        Map<String, String> errors = new HashMap<String, String>();

        reg.validateCustom(errors);
        logger.info(errors + " ");

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        if(errors.isEmpty()){
            logger.info("Without errors");
            page = "/visitor/index.jsp";
            RegistrationFormDTO registrationFormDTO = (RegistrationFormDTO)reg;

            logger.info(registrationFormDTO.getMemberType());
            accountService.enroll(registrationFormDTO);
            logger.info("Back at servlet");
        }
        request.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}

