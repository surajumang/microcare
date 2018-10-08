package com.care.controller;

import com.care.dao.MemberDAO;
import com.care.exception.BadRequestException;
import com.care.exception.MemberAlreadyRegisteredException;
import com.care.form.RegistrationForm;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CaptureRegistration extends Action {
    Logger logger = Logger.getLogger("Registration");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/visitor/registration.jsp";
        logger.info(page);
        OperationStatus operationStatus = OperationStatus.FAILURE;
        RegistrationForm registrationDetails = (RegistrationForm) form;
        logger.info(registrationDetails + " " );
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        logger.info(registrationDetails + "");

        try {
            operationStatus = accountService.enroll(registrationDetails);
            if (operationStatus == OperationStatus.SUCCESS){
                request.getSession().setAttribute(ControllerUtil.CURRENT_USER, accountService.getMember(registrationDetails.getEmail()));
            }
        }catch (Exception e){
            throw new BadRequestException(e);
        }
        logger.info("Back at servlet");
        request.setAttribute("formErrors", registrationDetails);
        return mapping.findForward("success");

    }
}
