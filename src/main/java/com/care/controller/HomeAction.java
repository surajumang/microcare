package com.care.controller;

import com.care.model.Member;
import com.care.model.MemberType;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class HomeAction extends Action {
    Logger logger = Logger.getLogger("HomeAction");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        MemberType memberType = member.getMemberType();


        return mapping.findForward(memberType.name().toLowerCase());
    }
}
