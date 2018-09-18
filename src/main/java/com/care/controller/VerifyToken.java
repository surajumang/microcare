package com.care.controller;

import com.care.model.Member;
import com.care.model.Status;
import com.care.model.Token;
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

public class VerifyToken extends Action {

    private Logger logger = Logger.getLogger("ResetPassword");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Invalid Token");
        message.put(OperationStatus.SUCCESS, "VERIFIED");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getParameter("token");
        String page = "/visitor/updatePassword.jsp";
        OperationStatus operationStatus = OperationStatus.FAILURE;

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        Token token1 = accountService.getToken(token);

        if (token1 != Token.emptyToken() && token1.getStatus()== Status.ACTIVE){
            request.setAttribute("id", token1.getMember().getId());
            request.setAttribute("token", token);
            logger.info("member's password resetting");

            operationStatus = OperationStatus.SUCCESS;
        }
        logger.info("Dispatching to ===" + page);
        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward("success");
    }
}
