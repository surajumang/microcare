package com.care.controller;

import com.care.dto.form.*;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Seeker;
import com.care.model.Sitter;
import com.care.service.*;
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

public class PutProfileInfo extends HttpServlet {

    Logger logger = Logger.getLogger("PutProfileInfo");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Member currentUser = (Member) request.getSession().getAttribute("currentUser");
        MemberType memberType = currentUser.getMemberType();
        String page = "/"+ memberType.name().toLowerCase()+"/PutProfileInfo.jsp";

        if (memberType == MemberType.SEEKER){

            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            Seeker profileInfo = seekerService.getSeeker(currentUser.getId());

            request.getSession().setAttribute("profileInfo", profileInfo);
            request.getRequestDispatcher(page).forward(request,response);
        }else {
            SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
            Sitter profileInfo = sitterService.getSitter(currentUser.getId());

            request.getSession().setAttribute("profileInfo", profileInfo);
            request.getRequestDispatcher(page).forward(request,response);
        }


    }
}
