package com.care.controller;

import com.care.dto.form.RegistrationFormDTO;
import com.care.dto.form.SeekerRegistrationDTO;
import com.care.dto.form.SitterRegistrationDTO;
import com.care.exception.MemberAlreadyRegisteredException;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Registration extends HttpServlet {
    Logger logger = Logger.getLogger("SeekerRegistration");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Unable to Register");
        message.put(OperationStatus.OTHER, "ALready Registered Try Forgot password");
        message.put(OperationStatus.SUCCESS, "Successful Registered, Now you can Log in");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/visitor/Registration.jsp";
        logger.info(page);

        OperationStatus operationStatus = OperationStatus.FAILURE;
        Class<? extends FormBean> detailPage;

        RegistrationFormDTO registrationDetails = FormPopulator.populate(request, RegistrationFormDTO.class);
        logger.info(registrationDetails + " " );
        Map<String, String> errors = new HashMap<String, String>();

        registrationDetails.validateCustom(errors);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        logger.info(errors + " ");
        logger.info(registrationDetails + "");
        request.setAttribute("errors", errors);

        if(errors.isEmpty()){
            page = "/index.jsp";

            try {
                operationStatus = accountService.enroll(registrationDetails);
            }catch (MemberAlreadyRegisteredException e){
                operationStatus = OperationStatus.OTHER;
            }
            logger.info("Back at servlet");
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));

        request.setAttribute("formErrors", (RegistrationFormDTO)registrationDetails);
        getServletContext().getRequestDispatcher(page).forward(request, response);

    }
}
