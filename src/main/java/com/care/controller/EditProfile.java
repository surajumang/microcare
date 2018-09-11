package com.care.controller;

import com.care.form.SeekerEditProfileForm;
import com.care.form.SitterEditProfileForm;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import com.care.validation.FormPopulator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class EditProfile extends Action {
    Logger logger = Logger.getLogger("CloseAccount");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Couldn't Close");
        message.put(OperationStatus.SUCCESS, "Successfully Closed Account");
    }

    private String editSeeker(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Map<String, String> errors){
        SeekerEditProfileForm seekerEditForm = (SeekerEditProfileForm)form;
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

    private String editSitter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Map<String, String> errors){
        SitterEditProfileForm sitterEditForm = (SitterEditProfileForm) form;
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

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member currentUser = (Member)request.getSession().getAttribute("currentUser");
        MemberType memberType = currentUser.getMemberType();
        Map<String, String> errors = new HashMap<>();

        String page = "/"+memberType.name().toLowerCase()+"/";

        if (memberType == MemberType.SEEKER){
            page += editSeeker(mapping, form, request, response, errors);
        }else {
            page += editSitter(mapping, form, request, response, errors);
        }
        request.setAttribute("errors", errors);
        return mapping.findForward(page);
    }
}
