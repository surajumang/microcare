package com.care.controller;

import com.care.dto.form.EditForm;
import com.care.dto.form.SeekerEditForm;
import com.care.dto.form.SitterEditForm;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Sitter;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormPopulator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member currentUser = (Member)request.getSession().getAttribute("currentUser");
        MemberType memberType = currentUser.getMemberType();
        Map<String, String> errors = new HashMap<>();

        String page = "/"+memberType.name().toLowerCase()+"/";

        if (memberType == MemberType.SEEKER){
            page += editSeeker(request, response, errors);
        }else {
            page += editSitter(request, response, errors);
        }
        request.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    private String editSeeker(HttpServletRequest request, HttpServletResponse response, Map<String, String> errors){
        SeekerEditForm seekerEditForm = FormPopulator.populate(request, SeekerEditForm.class);
        seekerEditForm.validateCustom(errors);
        String page = "PutProfileInfo.jsp";
        request.setAttribute("profileInfo", seekerEditForm);

        if (errors.isEmpty()){
            Member member = (Member) request.getSession().getAttribute("currentUser");
            AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
            accountService.editMember(member.getId(), seekerEditForm);

            request.setAttribute("SUCCESS", "Profile info edited successfully");
            page = "Home.jsp";
        }
        return page;
    }

    private String editSitter(HttpServletRequest request, HttpServletResponse response, Map<String, String> errors){
        SitterEditForm sitterEditForm = FormPopulator.populate(request, SitterEditForm.class);
        sitterEditForm.validateCustom(errors);
        String page = "PutProfileInfo.jsp";
        request.setAttribute("profileInfo", sitterEditForm);

        if (errors.isEmpty()){
            Member member = (Member) request.getSession().getAttribute("currentUser");
            AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
            accountService.editMember(member.getId(), sitterEditForm);

            request.setAttribute("SUCCESS", "Profile info edited successfully");
            page = "Home.jsp";
        }
        return page;
    }
}
