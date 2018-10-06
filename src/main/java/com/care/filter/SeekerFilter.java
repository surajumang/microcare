package com.care.filter;

import com.care.model.Member;
import com.care.model.MemberType;
import com.care.controller.ControllerUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class SeekerFilter implements Filter {
    private Logger logger = Logger.getLogger("LoginFilter");
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html");

        String appContext = request.getContextPath();
        String URI = request.getRequestURI();

        HttpSession session = request.getSession(false);
        Member currentUser = null;
        if (session != null){
            currentUser = (Member) session.getAttribute(ControllerUtil.CURRENT_USER);
        }

        if (currentUser == null){
            servletContext.getRequestDispatcher("/visitor/login.do?login=not").forward(request, response);
        }
        else if (currentUser.isSeeker()){
            logger.info("Member logged in-------------->>>>>>>>>");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            logger.info("Member not logged in-------------->>>>>>>>>>");
            request.setAttribute("message", "You must log in to view this content");
            servletContext.getRequestDispatcher("/member/home.do").forward(request, response);
            //response.sendRedirect(appContext+"/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
