package com.care.controller;

import com.care.dao.MemberDAO;
import com.care.dto.form.PasswordDTO;
import com.care.model.PasswordResetToken;
import com.care.service.*;
import com.care.validation.FormPopulator;
import com.care.validation.FormValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UpdatePassword extends HttpServlet {

    private Logger logger = Logger.getLogger("UpdatePassword");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Password Reset Failed");
        message.put(OperationStatus.SUCCESS, "Password Reset Successful, You can now log in");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*

         */
        String page = "/visitor/UpdatePassword.jsp";
        String token = req.getParameter("token");

        logger.info(token + "Updated");
        logger.info("**********" + req + "****************");

        AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
        PasswordDTO passwordDTO = FormPopulator.populate(req, PasswordDTO.class);

        Map<String, String> errors = new HashMap<>();
        passwordDTO.validateCustom(errors);
        logger.info(passwordDTO + " found");
        OperationStatus operationStatus = OperationStatus.FAILURE;

        if (errors.isEmpty()){
            operationStatus = authenticationService.updatePassword(passwordDTO);
            if (operationStatus == OperationStatus.SUCCESS){
                page = "/index.jsp";
            }
        }
        req.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(req, resp);

    }
}
