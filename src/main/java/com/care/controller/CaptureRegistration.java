package com.care.controller;

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
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Unable to Register");
        message.put(OperationStatus.OTHER, "ALready Registered Try Forgot password");
        message.put(OperationStatus.SUCCESS, "Successful Registered, Now you can Log in");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/visitor/registration.jsp";
        logger.info(page);
        OperationStatus operationStatus = OperationStatus.FAILURE;
        RegistrationForm registrationDetails = (RegistrationForm) form;
        logger.info(registrationDetails + " " );
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        logger.info(registrationDetails + "");
        page = "/login.jsp";

        try {
            operationStatus = accountService.enroll(registrationDetails);
        }catch (MemberAlreadyRegisteredException e){
            operationStatus = OperationStatus.OTHER;
        }
        logger.info("Back at servlet");
        request.setAttribute(operationStatus.name(), message.get(operationStatus));

        request.setAttribute("formErrors", registrationDetails);
        return mapping.findForward("success");

    }
}
