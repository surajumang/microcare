package com.care.controller;

import com.care.form.EditProfileForm;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Seeker;
import com.care.model.Sitter;
import com.care.service.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class PutProfileInfo extends Action {

    Logger logger = Logger.getLogger("PutProfileInfo");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member currentUser = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        MemberType memberType = currentUser.getMemberType();
        String page = "failure";
        EditProfileForm editProfileForm = (EditProfileForm)form;
        editProfileForm.setMemberType(memberType.name());

        if (memberType == MemberType.SEEKER){
            SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
            Seeker profileInfo = seekerService.getSeeker(currentUser.getId());
            page = "success";
            //Map the Seeker to a EditProfileForm. No need to attach to a request scope.
            ObjectMapper.mapObject(profileInfo, editProfileForm, false);
            request.getSession().setAttribute("profileInfo", profileInfo);
            //request.getRequestDispatcher(page).forward(request,response);
        }else {
            SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
            Sitter profileInfo = sitterService.getSitter(currentUser.getId());

            page = "success";
            ObjectMapper.mapObject(profileInfo, editProfileForm, false);
            request.getSession().setAttribute("profileInfo", profileInfo);
           // request.getRequestDispatcher(page).forward(request,response);
        }
        return mapping.findForward(page);
    }
}
