package com.care.controller;

import com.care.filter.HibernateFilter;
import com.care.form.PasswordResetForm;
import com.care.model.Status;
import com.care.model.Token;
import com.care.service.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
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
        String page = "failure";
        String token = request.getParameter("token");

        logger.info(token + "Updated");
        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        //PasswordResetForm passwordResetForm = FormPopulator.populate(request, PasswordResetForm.class);
        PasswordResetForm passwordResetForm = (PasswordResetForm) form ;
        logger.info(passwordResetForm + "---");

        logger.info(passwordResetForm + " found");
        // Double checking. Fetch the memberId and token status again so that Password gets updated only to the member to which
        // the token belongs to.
        OperationStatus operationStatus = OperationStatus.FAILURE;
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        try{
            Token token1 = accountService.getToken(token);
            if (token1 != Token.emptyToken() && token1.getStatus() != Status.EXPIRED){
                passwordResetForm.setId(String.valueOf(token1.getMember().getId()));

                operationStatus =
                        authenticationService.updatePasswordWithToken(passwordResetForm);
                if (operationStatus == OperationStatus.SUCCESS){
                    page = "success";
                    request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "True");
                }
            }

        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception while Resetting password using token.", e);
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward(page);
    }
}