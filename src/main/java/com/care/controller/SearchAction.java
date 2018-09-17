package com.care.controller;

import com.care.form.SearchCriteria;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Seeker;
import com.care.model.Sitter;
import com.care.service.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SearchAction extends Action {
    Logger logger = Logger.getLogger("SearchingMembers");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Couldn't find any Match");
        message.put(OperationStatus.SUCCESS, "Matches Found");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        SearchCriteria searchCriteria = (SearchCriteria) form;
        Member member = (Member) request.getSession().getAttribute("currentUser");
        OperationStatus operationStatus = OperationStatus.FAILURE;

        String page = "found";

        if(member.getMemberType() == MemberType.SITTER){
            SeekerService seekerService= ServiceFactory.get(SeekerServiceImpl.class);
            List<Seeker> seekers = seekerService.getSeekersByEmail(searchCriteria.getEmail());
            logger.info("Fetched Seekers " + seekers);
            if (!seekers.isEmpty()){
                operationStatus = OperationStatus.SUCCESS;
            }
            request.setAttribute("members", seekers);
        }
        else {
            SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
            List<Sitter> sitters = sitterService.getSittersByEmail(searchCriteria.getEmail());
            logger.info("Fetched sitters");
            if (!sitters.isEmpty()){
                operationStatus = OperationStatus.SUCCESS;
            }
            request.setAttribute("members", sitters);
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return mapping.findForward("success");
    }
}
