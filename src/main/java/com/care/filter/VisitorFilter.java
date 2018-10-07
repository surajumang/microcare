package com.care.filter;

import com.care.controller.ControllerUtil;
import com.care.model.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class VisitorFilter implements Filter {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ServletContext servletContext;
    /*
    Represents visitor pages which can be accessed by a user even though he is logged in.
     */
    private static final Set<String> ALLOWED_URL = new HashSet<>();
    static {
        ALLOWED_URL.add("captureToken");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        String[] uri = request.getRequestURI().split("/");
        String page = uri[uri.length > 0? uri.length -1 : 0];

        Member currentUser = null;

        if (session != null){
            currentUser = (Member) session.getAttribute(ControllerUtil.CURRENT_USER);
        }

        if (session==null || currentUser == null || checkURL(ALLOWED_URL, page) ){
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
    /*
    Check for the existense of partial page in the set.
     */
    private boolean checkURL(Set<String > allowed, String page){
        boolean value = false;
        for (String prefix:allowed) {
            if (page.startsWith(prefix)){
                value = true;
                break;
            }
        }
        return value;
    }

    @Override
    public void destroy() {

    }
}
