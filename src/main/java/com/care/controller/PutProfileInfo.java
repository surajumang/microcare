package com.care.controller;

import com.care.filter.HibernateFilter;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class PutProfileInfo extends Action {

    Logger logger = Logger.getLogger("PutProfileInfo");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member currentUser = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        MemberType memberType = currentUser.getMemberType();

        EditProfileForm editProfileForm = (EditProfileForm)form;
        editProfileForm.setMemberType(memberType.name());

        try {
            if (currentUser.isSeeker()){
                SeekerService seekerService = ServiceFactory.get(SeekerServiceImpl.class);
                Seeker profileInfo = seekerService.getSeeker(currentUser.getId());

                //Map the Seeker to a EditProfileForm. No need to attach to a request scope.
                ObjectMapper.mapObject(profileInfo, editProfileForm, false);
                request.getSession().setAttribute("profileInfo", profileInfo);
            }else {
                SitterService sitterService = ServiceFactory.get(SitterServiceImpl.class);
                Sitter profileInfo = sitterService.getSitter(currentUser.getId());

                ObjectMapper.mapObject(profileInfo, editProfileForm, false);
                request.getSession().setAttribute("profileInfo", profileInfo);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "showing profile for edit", e);
        }
        request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "True");
        return mapping.findForward("success");
    }
}
