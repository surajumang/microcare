
package com.care.controller;

import com.care.model.Member;
import com.care.model.MemberType;
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

    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Invalid credentials");
        message.put(OperationStatus.SUCCESS, "");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginDetails userLoginDetails = FormPopulator.populate(request, LoginDetails.class);
        Map<String, String> errors = new HashMap<String, String>();
        userLoginDetails.validateCustom(errors);

        logger.info(userLoginDetails.toString());

        String page = "/index.jsp";
        OperationStatus status = OperationStatus.FAILURE;

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        logger.info(errors + "");
        request.setAttribute("errors", errors);

        if (errors.isEmpty()) {
            status = authenticationService.loginUser(userLoginDetails);

            if (status == OperationStatus.SUCCESS){
                Member member = accountService.getMember(userLoginDetails.getEmail());
                if (member != Member.EMPTY_MEMBER){

                    request.getSession().setAttribute("currentUser" ,member);
                    logger.info("Member set to sesion" + member);
                    String memberType = member.getMemberType().name().toLowerCase();

                    request.getSession().setAttribute("memberType" , memberType);
                    logger.info("Back at LoginServlet");

                    page = setMemberPage(member.getMemberType());
                }
            }
            request.setAttribute(status.name(), message.get(status));
        }
        request.setAttribute(status.name(), message.get(status));
        request.setAttribute("loginDetails", userLoginDetails);
        logger.info("Dispatching to" + page);
        getServletContext().getRequestDispatcher(page).forward(request,response);
    }

    private String setMemberPage(MemberType memberType){
        String page = "";
        if (memberType == MemberType.SEEKER){
            page += "/seeker/Home.jsp";
        }else{
            page += "/sitter/Home.jsp";
        }
        return page;
    }
}
