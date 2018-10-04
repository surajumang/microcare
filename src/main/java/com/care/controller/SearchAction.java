package com.care.controller;

import com.care.form.SearchForm;
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

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        SearchForm searchForm = (SearchForm) form;
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        OperationStatus operationStatus = OperationStatus.FAILURE;
        String page = "found";
        request.setAttribute("email", searchForm.getEmail());

        if(member.getMemberType() == MemberType.SITTER){
            SeekerService seekerService= ServiceFactory.get(SeekerServiceImpl.class);
            List<Seeker> seekers = seekerService.getSeekersByEmail(searchForm.getEmail());
            logger.info("Fetched Seekers " + seekers);
            request.setAttribute("members", seekers);
        }
        else {
            SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
            List<Sitter> sitters = sitterService.getSittersByEmail(searchForm.getEmail());
            logger.info("Fetched sitters");
            request.setAttribute("members", sitters);
        }
        return mapping.findForward("success");
    }
}
