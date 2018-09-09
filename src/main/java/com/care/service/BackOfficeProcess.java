package com.care.service;

import com.care.dao.*;

import javax.servlet.Filter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackOfficeProcess implements ServletContextListener {

    private static Logger logger = Logger.getLogger("BackOfficeProcess");

    private Thread backOfficeThread;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //start a thread
        backOfficeThread = new Thread(new BackOfficeThread(),"BackOffice Thread");
        backOfficeThread.setDaemon(true);
        backOfficeThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        
    }

    private static class BackOfficeThread implements Runnable{
        @Override
        public void run() {
            while (true){
                logger.info("BackOffice run");
                JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
                MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);

                try {
                    int job = jobDAO.expireStaleJobs();
                    int tokens = memberDAO.expireStaleTokens();

                    logger.info("Deleted jobs" + job);
                    logger.info("Deleted tokens" + tokens);
                    Thread.sleep(1000*60*30);
                }catch (SQLException | InterruptedException e){
                    logger.log(Level.SEVERE, "can't delete ", e);

                }
            }
        }
    }

}
