package com.care.service;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;
import com.care.dao.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SitterServiceImpl implements SitterService {

    private Logger logger = Logger.getLogger("SitterServiceImpl");
    private static SitterServiceImpl ourInstance = new SitterServiceImpl();

    public static SitterServiceImpl getInstance(){
        return ourInstance;
    }

    private SitterServiceImpl(){

    }

    public List<Job> listAllJobs() {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> allJobs;

        try{
            allJobs = jobDAO.getAllJobs();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Getting all Jobs" , e);
            allJobs = Collections.emptyList();
        }
        return allJobs;
    }

    public List<Application> listAllApplications(Member member, int sitterId) {
        return null;
    }

    public List<Application> listApplication(Member member, int applicationId) {
        return null;
    }

    public int applyToJob(Member member, int jobId) {

        return 0;
    }

    public int deleteApplication(Member member, int applicationId) {
        int status = 0;
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);

        try {
            status = applicationDAO.deleteApplication(member, applicationId);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Error Closing application", e);
        }
        return status;
    }
}
