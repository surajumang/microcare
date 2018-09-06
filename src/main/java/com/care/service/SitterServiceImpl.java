package com.care.service;

import com.care.model.Application;
import com.care.model.Job;
import com.care.dao.*;
import com.care.model.Member;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SitterServiceImpl implements SitterService {

    private Logger logger = Logger.getLogger("SitterServiceImpl");

    public SitterServiceImpl(){ }

    public List<Job> listAllAvailableJobs(Member sitter) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> allJobs;

        try{
            allJobs = jobDAO.getAllAvailableJobs(sitter.getId());
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Getting all Available Jobs" , e);
            allJobs = Collections.emptyList();
        }
        return allJobs;
    }

    public List<Application> listAllApplications(Member sitter) {
        ApplicationDAO applicationDAO= DAOFactory.get(ApplicationDAOImpl.class);
        List<Application> applications ;
        try{
            applications = applicationDAO.getAllApplications(sitter.getId());
        }catch (SQLException e){
            logger.log(Level.SEVERE , "All getApplications for Sitter", e);
            applications = Collections.emptyList();
        }
        return applications;
    }

    public int applyToJob(Member sitter, int jobId) {

        return 0;
    }

    public int deleteApplication(Member sitter, int applicationId) {
        int status = 0;
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        try {
            status = applicationDAO.deleteApplication(applicationId);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Error Closing application", e);
        }
        return status;
    }
}
