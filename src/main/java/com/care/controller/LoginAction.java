
package com.care.controller;

import com.care.form.LoginForm;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Status;
import com.care.service.*;
import com.care.validation.FormPopulator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginAction extends Action {
    Logger logger = Logger.getLogger("LoginServlet");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginForm userLoginForm = (LoginForm) form;
        logger.info(userLoginForm.toString());

        String page = "failure";
        OperationStatus status;
        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        try{
            status = authenticationService.loginUser(userLoginForm);
            if (status == OperationStatus.SUCCESS){
                Member member = accountService.getMember(userLoginForm.getEmail());

                if (! member.isEmpty() && member.isActive()){
                    request.getSession().setAttribute(ControllerUtil.CURRENT_USER ,member);
                    logger.info("Member set to sesion" + member);
                    String memberType = member.getMemberType().name().toLowerCase();
                    request.getSession().setAttribute("flow" , memberType);
                    logger.info("Back at LoginServlet");
                    page = "success";
                }
            }
        }catch(Exception e){
            logger.log(Level.SEVERE, "While login ", e);
        }

        request.setAttribute("loginDetails", userLoginForm);
        logger.info("Dispatching to" + page);

        return mapping.findForward(page);
    }
}
