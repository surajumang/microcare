package com.care.filter;

import com.care.beans.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginFilter implements Filter {

    private Logger logger = Logger.getLogger("LoginFilter");

    private ServletContext servletContext;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //cast the Request and response parameter to Http.
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html");

        String appContext = request.getContextPath();

        String URI = request.getRequestURI();
        HttpSession session = request.getSession(false);

        logger.info(" inside LoginFilter");

        logger.info(URI + "-- -" + session);
        boolean userloggedIn = false;

        if (session != null){
            logger.info("Session Not null");
            Member member = (Member)session.getAttribute("currentUser");
            if (member != null){
                logger.info(member.toString());
                userloggedIn = true;
            }
            else
                logger.info("Member Nnull");
        }

        boolean startPage = URI.startsWith(appContext+"/index.jsp") || URI.equals(appContext) ||
                URI.startsWith(appContext+"/login.do");
        if(startPage){
            logger.info("Start Pgae");
        }
        if (userloggedIn){

        }

        if (userloggedIn || startPage){
            logger.info("chained");
            filterChain.doFilter(servletRequest, servletResponse);

        }else {
            logger.info("Member not logged in");
            request.setAttribute("message", "You must log in first");
            servletContext.getRequestDispatcher("/index.jsp").forward(request, response);
            //response.sendRedirect(appContext+"/index.jsp");
        }

    }

    public void destroy() {

    }
}
