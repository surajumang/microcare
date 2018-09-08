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
import java.util.logging.Logger;

public class Search extends HttpServlet {
    Logger logger = Logger.getLogger("SearchingMembers");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Couldn't find any Match");
        message.put(OperationStatus.SUCCESS, "Matches Found");
    }
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
        OperationStatus operationStatus = OperationStatus.FAILURE;

        String page = "/member/Search.jsp";
        logger.info(errors + " ");

        if(errors.isEmpty()){
            if(member.getMemberType() == MemberType.SITTER){
                SeekerService seekerService= ServiceFactory.get(SeekerServiceImpl.class);
                List<Seeker> seekers = seekerService.getSeekerByEmail(searchCriteria.getEmail());
                logger.info("Fetched Seekers " + seekers);
                if (!seekers.isEmpty()){
                    operationStatus = OperationStatus.SUCCESS;
                }
                req.setAttribute("type", "SEEKER");
                req.setAttribute("members", seekers);
            }
            else {
                SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
                List<Sitter> sitters = sitterService.getSitterByEmail(searchCriteria.getEmail());
                logger.info("Fetched sitters");
                if (!sitters.isEmpty()){
                    operationStatus = OperationStatus.SUCCESS;
                }
                req.setAttribute("type", "SITTER");
                req.setAttribute("members", sitters);
            }
        }

        req.setAttribute(operationStatus.name(), message.get(operationStatus));
        req.setAttribute("errors", errors);
        getServletContext().getRequestDispatcher(page).forward(req, resp);

    }
}
