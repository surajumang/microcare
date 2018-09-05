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

    public SitterServiceImpl(){ }

    public List<Job> listAllAvailableJobs(Member member) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> allJobs;

        try{
            allJobs = jobDAO.getAllAvailableJobs(member.getId());
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Getting all Available Jobs" , e);
            allJobs = Collections.emptyList();
        }
        return allJobs;
    }

    public List<Application> listAllApplications(Member member) {
        ApplicationDAO applicationDAO= DAOFactory.get(ApplicationDAOImpl.class);
        List<Application> applications ;
        try{
            applications = applicationDAO.getAllApplications(member.getId());
        }catch (SQLException e){
            logger.log(Level.SEVERE , "All applications for Sitter", e);
            applications = Collections.emptyList();
        }
        return applications;
    }

    public int applyToJob(Member member, int jobId) {

        return 0;
    }

    public int deleteApplication(Member member, int applicationId) {
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
