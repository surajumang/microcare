package com.care.controller;

import com.care.dto.form.SeekerRegistrationDTO;
import com.care.dto.form.SitterRegistrationDTO;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Seeker;
import com.care.model.Sitter;
import com.care.service.*;
import com.care.validation.FormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class ShowProfileToEdit extends HttpServlet {
    Logger logger = Logger.getLogger("ShowProfileToEdit");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do a db call and fetch all the details of the member(seeker/sitter).
        String page = request.getParameter("currentPage");
        logger.info(page);
        Member member = (Member)request.getAttribute("currentUser");
        MemberType memberType = member.getMemberType();

        if (memberType == MemberType.SEEKER){

        }else{

        }

    }

    private Seeker fetchSeeker(int memberId){
        SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
        //do a instanceof test.
        return seekerService.getSeeker(memberId);
    }

    private Sitter fetchSitter(int memberId){
        SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
        return sitterService.getSitter(memberId);
    }
}
