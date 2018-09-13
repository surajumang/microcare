package com.care.controller;

import com.care.form.EditProfileForm;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class EditProfileAction extends Action {
    Logger logger = Logger.getLogger("CloseAccount");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Couldn't Edit profile");
        message.put(OperationStatus.SUCCESS, "Successfully Edited your profile");
    }

//    private String editSeeker(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Map<String, String> errors){
//        SeekerEditProfileForm seekerEditForm = (SeekerEditProfileForm)form;
//        String page = "putProfileInfo.jsp";
//        request.setAttribute("profileInfo", seekerEditForm);
//
//        if (errors.isEmpty()){
//            Member member = (Member) request.getSession().getAttribute("currentUser");
//            AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
//            accountService.editMember(member.getId(), seekerEditForm);
//
//            request.setAttribute("SUCCESS", "Profile info edited successfully");
//            page = "home.jsp";
//        }
//        return page;
//    }
//
//    private String editSitter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Map<String, String> errors){
//        SitterEditProfileForm sitterEditForm = (SitterEditProfileForm) form;
//        String page = "putProfileInfo.jsp";
//        request.setAttribute("profileInfo", sitterEditForm);
//
//        if (errors.isEmpty()){
//            Member member = (Member) request.getSession().getAttribute("currentUser");
//            AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
//            accountService.editMember(member.getId(), sitterEditForm);
//
//            request.setAttribute("SUCCESS", "Profile info edited successfully");
//            page = "home.jsp";
//        }
//        return page;
//    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member currentUser = (Member)request.getSession().getAttribute("currentUser");
        MemberType memberType = currentUser.getMemberType();

        String page = "/member/home.do";
        EditProfileForm editProfileForm = (EditProfileForm)form;
        OperationStatus operationStatus = OperationStatus.FAILURE;

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        int status = accountService.editMember(currentUser.getId(), editProfileForm);
        if (status == 1){
            operationStatus = OperationStatus.SUCCESS;
        }

        request.setAttribute(operationStatus.name(), message.get(operationStatus));
        return new ActionForward(page);
    }
}
