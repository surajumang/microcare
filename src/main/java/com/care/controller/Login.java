
package com.care.controller;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dto.form.LoginDetails;
import com.care.service.*;
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

        String page = "/ErrorPage.jsp";

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        if (errors.isEmpty()) {
            LoginDetails loginDetails = (LoginDetails) userLoginDetails;

            if (authenticationService.loginUser(loginDetails)){
                Member member = accountService.getMember(loginDetails.getEmail());
                if (member != Member.EMPTY_MEMBER){
                    request.getSession().setAttribute("currentUser" ,member);
                    logger.info("Back at LoginServlet");
                    page = setMemberPage(member.getMemberType());
                }

            }
        }
        getServletContext().getRequestDispatcher(page).forward(request, response);

    }

    private String setMemberPage(MemberType memberType){
        String page = "";
        if (memberType == MemberType.SEEKER){
            page += "/seeker/Home.jsp";
        }else{
            page += "/sitter/Home.jsp";
        }
        logger.info("done with page" + page);
        return page;
    }
}
