package com.care.filter;

import com.care.dao.HibernateUtil;
import org.hibernate.*;
import org.hibernate.context.internal.ManagedSessionContext;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateFilter implements Filter {
    private Logger logger = Logger.getLogger("HibrenateFilter");
    //private static Log log = LogFactory.getLog(HibernateSessionConversationFilter.class);

    private SessionFactory sf;

    public static final String HIBERNATE_SESSION_KEY = "hibernateSession";
    public static final String END_OF_CONVERSATION_FLAG = "endOfConversation";

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        Session currentSession;

        // Try to get a Hibernate Session from the HttpSession  
        HttpSession httpSession =
                ((HttpServletRequest) request).getSession();
        Session disconnectedSession =
                (Session) httpSession.getAttribute(HIBERNATE_SESSION_KEY);

        try {
            // Start a new conversation or in the middle?  
            if (disconnectedSession == null) {
                logger.info(">>> New conversation");
                currentSession = sf.openSession();
                currentSession.setFlushMode(FlushMode.MANUAL);
            } else {
                logger.info("< Continuing conversation");
                currentSession = disconnectedSession;
            }

            logger.info("Binding the current Session");
            ManagedSessionContext.bind(currentSession);

            logger.info("Starting a database transaction");
            currentSession.beginTransaction();

            logger.info("Processing the event");
            chain.doFilter(request, response);

            logger.info("Unbinding Session after processing");
            currentSession = ManagedSessionContext.unbind(sf);

            // End or continue the long-running conversation?  
            if (request.getAttribute(END_OF_CONVERSATION_FLAG) != null ||
                    request.getParameter(END_OF_CONVERSATION_FLAG) != null) {

                logger.info("Flushing Session");
                currentSession.flush();

                logger.info("Committing the database transaction");
                currentSession.getTransaction().commit();

                logger.info("Closing the Session");
                currentSession.close();

                logger.info("Cleaning Session from HttpSession");
                httpSession.setAttribute(HIBERNATE_SESSION_KEY, null);

                logger.info("<<< End of conversation");

            } else {

                logger.info("Committing database transaction");
                currentSession.getTransaction().commit();

                logger.info("Storing Session in the HttpSession");
                httpSession.setAttribute(HIBERNATE_SESSION_KEY, currentSession);

                logger.info("> Returning to user in conversation");
            }

        } catch (StaleObjectStateException staleEx) {
            
            // Rollback, close everything, possibly compensate for any permanent changes  
            // during the conversation, and finally restart business conversation. Maybe  
            // give the user of the application a chance to merge some of his work with  
            // fresh data... what you do here depends on your applications design.  
            throw staleEx;
        } catch (Throwable ex) {
            // Rollback only  
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    logger.info("Trying to rollback database transaction after exception");
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                logger.log(Level.SEVERE, "Could not rollback transaction after exception!", rbEx);
            } finally {
                logger.log(Level.SEVERE,"Cleanup after exception!");

                // Cleanup  
                logger.info("Unbinding Session after exception");
                currentSession = ManagedSessionContext.unbind(sf);

                logger.info("Closing Session after exception");
                currentSession.close();

                logger.info("Removing Session from HttpSession");
                httpSession.setAttribute(HIBERNATE_SESSION_KEY, null);

            }

            // Let others handle it... maybe another interceptor for exceptions?  
            throw new ServletException(ex);
        }

    }
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing filter...");
        logger.info("Obtaining SessionFactory from static HibernateUtil singleton");
        sf = HibernateUtil.getSessionFactory();
    }

    public void destroy() {}
}
