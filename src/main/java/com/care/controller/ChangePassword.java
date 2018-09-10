package com.care.controller;

import com.care.dto.form.PasswordDTO;
import com.care.model.Member;
import com.care.service.AuthenticationService;
import com.care.service.AuthenticationServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import com.care.validation.FormPopulator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ChangePassword extends HttpServlet {
    Logger logger = Logger.getLogger("CHnagePassword");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();

    static {
        message.put(OperationStatus.FAILURE, "Invalid Password");
        message.put(OperationStatus.SUCCESS, "Password Updated Successfully");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = (Member)req.getSession().getAttribute("currentUser");

        String page = "/member/UpdatePassword.jsp";
        PasswordDTO passwordDTO = FormPopulator.populate(req, PasswordDTO.class);
        passwordDTO.setId(String.valueOf(member.getId()));

        Map<String, String> errors = new HashMap<>();
        passwordDTO.validateCustom(errors);

        OperationStatus operationStatus = OperationStatus.FAILURE;

        req.setAttribute("errors", errors);
        if (errors.isEmpty()){
            AuthenticationService authenticationService = ServiceFactory.get(AuthenticationServiceImpl.class);
            operationStatus = authenticationService.updatePassword(member, passwordDTO);
            page = "/"+ member.getMemberType().name().toLowerCase() + "/Home.jsp";
        }

        req.setAttribute(operationStatus.name(), message.get(operationStatus));
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
