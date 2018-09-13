package com.care.controller;

import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Status;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.OperationStatus;
import com.care.service.ServiceFactory;
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

public class LoginClosedMember extends Action {
    Logger logger = Logger.getLogger("LoginClosedMember");
    private static final Map<OperationStatus, String> message = new HashMap<OperationStatus, String>();
    static {
        message.put(OperationStatus.FAILURE, "Invalid credentials");
        message.put(OperationStatus.SUCCESS, "");
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute("closedUser");
        String page = "failure";
        OperationStatus operationStatus = OperationStatus.FAILURE;
        String userResponse = request.getParameter("response");
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);

        if (member != null && userResponse != null && userResponse.equals("Yes")){

            accountService.setMemberStatus(member, Status.ACTIVE);
            member.setStatus(Status.ACTIVE);
            request.getSession().setAttribute("currentUser" ,member);
            logger.info("Member set to sesion" + member);
            String memberType = member.getMemberType().name().toLowerCase();

            request.getSession().setAttribute("memberType" , memberType);
            logger.info("Back at LoginServlet");

            page = "success";
        }

        return mapping.findForward(page);

    }

    private String setMemberPage(MemberType memberType){
        String page = "";
        if (memberType == MemberType.SEEKER){
            page += "/seeker/home.jsp";
        }else{
            page += "/sitter/home.jsp";
        }
        return page;
    }
}
