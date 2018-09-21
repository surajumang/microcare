package com.care.filter;

import com.care.controller.ControllerUtil;
import com.care.model.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class VisitorFilter implements Filter {
    private Logger logger = Logger.getLogger(this.getClass().getName());
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
        HttpSession session = request.getSession(false);

        Member currentUser = null;
        if (session != null){
            currentUser = (Member) session.getAttribute(ControllerUtil.CURRENT_USER);
        }

        if (currentUser == null){
            logger.info("Member NOt logged in-------------->>>>>>>>>");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            logger.info("Member logged in-------------->>>>>>>>>>");
//            request.setAttribute("message", "You must log in first");
            servletContext.getRequestDispatcher("/member/home.do").forward(request, response);
            //response.sendRedirect(appContext+"/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
