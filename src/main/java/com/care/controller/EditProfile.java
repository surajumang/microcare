package com.care.controller;

import com.care.dto.form.RegistrationFormDTO;
import com.care.dto.form.SeekerRegistrationDTO;
import com.care.dto.form.SitterRegistrationDTO;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
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

public class EditProfile extends HttpServlet {
    Logger logger = Logger.getLogger("EditProfile");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("currentPage");
        MemberType memberType = MemberType.valueOf(request.getParameter("memberType"));
        Class<? extends FormBean> detailPage;

        if (memberType == MemberType.SEEKER){
            detailPage = SeekerRegistrationDTO.class;
        }
        else {
            detailPage = SitterRegistrationDTO.class;
        }

        FormBean registrationDetails = FormPopulator.populate(request, detailPage);
        logger.info(registrationDetails + " " );
        Map<String, String> errors = new HashMap<String, String>();

        registrationDetails.validateCustom(errors);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        logger.info(errors + " ");
        request.setAttribute("errors", errors);
        if(errors.isEmpty()){
            page = "/"+memberType.name().toLowerCase()+"/Home.jsp";
            RegistrationFormDTO registrationFormDTO = (RegistrationFormDTO)registrationDetails;

            logger.info("Without errors");
            logger.info(registrationFormDTO.getMemberType());
            /*
            Set unmodifiebale attributes here.
             */
            accountService.enroll(registrationFormDTO);
            logger.info("Back at servlet");
        }

        request.setAttribute("formErrors", (RegistrationFormDTO)registrationDetails);
        request.getRequestDispatcher(page).forward(request,response);

    }
}
