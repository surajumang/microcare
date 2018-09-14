package com.care.controller;

import com.care.form.PasswordForm;
import com.care.service.*;
import com.care.validation.FormPopulator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ResetPassword extends Action {
    /*
    It will reset password after verifying that the user got the link in their mail.
     */

    private Logger logger = Logger.getLogger("ResetPassword");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Password Reset Failed");
        message.put(OperationStatus.SUCCESS, "Password Reset Successful, You can now log in");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = "/visitor/updatePassword.jsp";
        String token = request.getParameter("token");

        logger.info(token + "Updated");
        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        PasswordForm passwordForm = FormPopulator.populate(request, PasswordForm.class);

        logger.info(passwordForm + " found");
        OperationStatus operationStatus =
                authenticationService.updatePasswordWithToken(passwordForm);
        if (operationStatus == OperationStatus.SUCCESS){
            page = "/login.jsp";
        }
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward("success");
    }
}
