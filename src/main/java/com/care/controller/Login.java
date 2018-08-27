
package com.care.controller;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dto.form.LoginDetails;
import com.care.service.SeekerServiceImpl;
import com.care.validation.FormBean;
import com.care.validation.FormPopulator;
import com.care.validation.FormValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FormBean ld = FormPopulator.populate(req, LoginDetails.class);

        System.err.println(ld);
        Map<String, String> m = new HashMap<String, String>();
        req.setAttribute("errors", m);

        FormValidator.validate(ld, req);
        System.err.println(m);

        String page = "jsp/Members/ErrorPage.jsp";

        if (m.isEmpty()) {
            LoginDetails lld = (LoginDetails) ld;
            Member member = SeekerServiceImpl.login(lld);
            MemberType memberType = member.getMemberType();

            if (lld.getEmail().equals(member.getEmail()))
                if (lld.getPassword().equals(member.getPassword())) {

                    if (memberType == MemberType.SEEKER){
                        page = "jsp/Members/seeker/Home.jsp";
                    }
                    else{
                        page = "jsp/Members/sitter/Home.jsp";
                    }
                }
        }
        RequestDispatcher rd = req.getRequestDispatcher(page);
        rd.forward(req, resp);
    }

    private void startSession(Member member, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", member);
    }
}
