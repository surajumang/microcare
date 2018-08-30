
package com.care.controller;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dto.form.LoginDetails;
import com.care.service.AuthenticationService;
import com.care.service.AuthenticationServiceImpl;
import com.care.service.AuthenticationUtil;
import com.care.service.ServiceFactory;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

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

public class Login extends HttpServlet{

    Logger logger = Logger.getLogger("LoginServlet");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FormBean userLoginDetails = FormPopulator.populate(req, LoginDetails.class);

        logger.info(userLoginDetails.toString());
        Map<String, String> errors = new HashMap<String, String>();

        //FormValidator.validate(userLoginDetails, errors);
       // logger.info(errors.toString());

        String page = "jsp/Members/ErrorPage.jsp";

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);

        if (errors.isEmpty()) {
            LoginDetails loginDetails = (LoginDetails) userLoginDetails;
            // handle the case
            Member member = null;
            if (authenticationService.loginUser(loginDetails)){
                member = AuthenticationUtil.getLoggedInUser();
            }

            MemberType memberType = member.getMemberType();

        }
        RequestDispatcher rd = req.getRequestDispatcher(page);
        rd.forward(req, resp);
    }
}
