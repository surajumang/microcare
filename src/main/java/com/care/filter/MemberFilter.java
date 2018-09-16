package com.care.filter;

import com.care.model.Member;
import com.care.model.MemberType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class MemberFilter implements Filter {
    private Logger logger = Logger.getLogger("LoginFilter");
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
        Check if the logged in member is a seeker or a sitter and redirect according to the requested page.
         */
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html");

        String appContext = request.getContextPath();
        String URI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        //check if session != null only then proceed
        Member currentUser = null;
        if (session != null){
            currentUser = (Member) session.getAttribute("currentUser");
        }

        if (currentUser != null){
            logger.info("Member logged in-------------->>>>>>>>>");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            logger.info("Member not logged in-------------->>>>>>>>>>");
            request.setAttribute("message", "You must log in first");
            servletContext.getRequestDispatcher("/visitor/login.do").forward(request, response);
            //response.sendRedirect(appContext+"/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
