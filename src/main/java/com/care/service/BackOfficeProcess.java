package com.care.service;

import com.care.dao.*;

import javax.servlet.Filter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackOfficeProcess  {

    private static Logger logger = Logger.getLogger("BackOfficeProcess");

    private Thread backOfficeThread;

//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        //start a thread
//        backOfficeThread = new Thread(new BackOfficeThread(),"BackOffice Thread");
//        backOfficeThread.setDaemon(true);
//        backOfficeThread.start();
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }

    private static class BackOfficeThread implements Runnable{
        @Override
        public void run() {
            while (true){
                logger.info("BackOffice run");
                JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
                MemberDAO memberDAO = DAOFactory.get(HMemberDAOImpl.class);

                try {
                    int job = jobDAO.expireStaleJobs();
                    int tokens = memberDAO.expireStaleTokens();

                    logger.info("Deleted jobs" + job);
                    logger.info("Deleted tokens" + tokens);
                    Thread.sleep(1000*60*30);
                }catch (Exception e){
                    logger.log(Level.SEVERE, "can't delete ", e);
                }
            }
        }
    }

}
