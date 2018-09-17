package com.care.filter;

import com.care.dao.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateFilter implements Filter {
    Logger logger = Logger.getLogger("HibrenateFilter");
    private static SessionFactory sessionFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("At HIBERNATE FILTER");
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "Can't create a Session", e);
        }

        try {
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "Can't commit a session", e);
        }
    }

    @Override
    public void destroy() {

    }
}
