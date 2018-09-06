package com.care.controller;

import com.care.dto.form.*;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
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

public class EditProfile extends HttpServlet {

    Logger logger = Logger.getLogger("EditProfile");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Member currentUser = (Member) request.getSession().getAttribute("currentUser");
        MemberType memberType = currentUser.getMemberType();
        String page = "/"+ memberType.name().toLowerCase()+"/EditProfile.jsp";

        Class<? extends FormBean> detailPage;

        if (memberType == MemberType.SEEKER){
            detailPage = SeekerEditForm.class;
        }
        else {
            detailPage = SitterEditForm.class;
        }

        FormBean editDetails = FormPopulator.populate(request, detailPage);
        logger.info(editDetails + " " );
        Map<String, String> errors = new HashMap<String, String>();

        editDetails.validateCustom(errors);
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        logger.info(errors + " ");
        request.setAttribute("errors", errors);
        if(errors.isEmpty()){
            page = "/"+memberType.name().toLowerCase()+"/Home.jsp";
            EditForm editForm = (EditForm) editDetails;

            logger.info("Without errors");
            logger.info(editForm.getMemberType());

            accountService.editMember(currentUser.getId(), editForm);
            logger.info("Back at servlet");
        }

        if (memberType == MemberType.SEEKER){
            request.setAttribute("formErrors", (SeekerEditForm)editDetails);
        }else{
            request.setAttribute("formErrors", (SitterEditForm)editDetails);
        }

        request.getRequestDispatcher(page).forward(request,response);

    }
}
