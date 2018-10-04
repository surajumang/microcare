package com.care.controller;

import com.care.filter.HibernateFilter;
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

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member currentUser = (Member)request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        MemberType memberType = currentUser.getMemberType();

        String page = "/member/home.do";
        EditProfileForm editProfileForm = (EditProfileForm)form;
        editProfileForm.setMemberType(memberType.name());

        OperationStatus operationStatus = OperationStatus.FAILURE;

        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
        int status = accountService.editMember(currentUser.getId(), editProfileForm);
        if (status == 1){
            operationStatus = OperationStatus.SUCCESS;
            request.getSession().setAttribute(ControllerUtil.CURRENT_USER, accountService.getMember(currentUser.getId()));
            request.setAttribute(HibernateFilter.END_OF_CONVERSATION_FLAG, "True");
        }

        return mapping.findForward("success");
    }
}
