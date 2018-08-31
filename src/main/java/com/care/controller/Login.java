
package com.care.controller;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dto.User;
import com.care.dto.form.LoginDetails;
import com.care.exception.IncompatibleUserTypeException;
import com.care.exception.NoUserLoggedInException;
import com.care.service.*;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends HttpServlet{

    Logger logger = Logger.getLogger("LoginServlet");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FormBean userLoginDetails = FormPopulator.populate(request, LoginDetails.class);

        logger.info(userLoginDetails.toString());
        Map<String, String> errors = new HashMap<String, String>();

        //FormValidator.validate(userLoginDetails, errors);
       // logger.info(errors.toString());

        String page = "Members/ErrorPage.jsp";

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        MemberService memberService = ServiceFactory.get(MemberServiceImpl.class);

        if (errors.isEmpty()) {
            LoginDetails loginDetails = (LoginDetails) userLoginDetails;
            // handle the case
            Member member = null;
            MemberType memberType = null;

            if (authenticationService.loginUser(loginDetails)){
                member = memberService.getMember(loginDetails.getEmail());
                logger.info("Back at LoginServlet");
                try {
                    CommonUtil.setLoggedInUser(member, request);
                }catch (IncompatibleUserTypeException e) {
                    logger.log(Level.SEVERE, "Can't set a Member to Request", e.getCause());
                }

                User user = new User();
                ObjectMapper.mapObject(member, user, true);
                request.setAttribute("member", member);
                memberType = member.getMemberType();

                if (memberType.name().equals("SEEKER")){
                    logger.info("Seeker");
                    page = "Members/Seeker/Home.jsp";
                }
                else
                    page = "Members/Sitter/Home.jsp";
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
