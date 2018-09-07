package com.care.controller;

import com.care.dto.form.SearchCriteria;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Seeker;
import com.care.model.Sitter;
import com.care.service.*;
import com.care.validation.FormPopulator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        SearchCriteria searchCriteria = FormPopulator.populate(req, SearchCriteria.class);
        searchCriteria.validateCustom(errors);

        Member member = (Member) req.getSession().getAttribute("currentUser");

        String page = "/member/Search.jsp";
        if(errors.isEmpty()){
            if(member.getMemberType() == MemberType.SEEKER){
                SeekerService seekerService= ServiceFactory.get(SeekerServiceImpl.class);
                List<Seeker> seekers = seekerService.getSeekerByEmail(searchCriteria.getEmail());
                req.setAttribute("members", seekers);
            }
            else {
                SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
                List<Sitter> sitters = sitterService.getSitterByEmail(searchCriteria.getEmail());
                req.setAttribute("members", sitters);
            }
        }
        getServletContext().getRequestDispatcher(page).forward(req, resp);

    }
}
