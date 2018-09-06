package com.care.service;

import com.care.dto.form.ApplicationDTO;
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

    @Override
    public Job getJob(int jobId) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = Job.EMPTY_JOB;
        try {
            job = jobDAO.getJob(jobId);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "While getting a Job", e);
        }
        return job;
    }

    public int applyToJob(ApplicationDTO applicationDTO) {

        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        Application application = new Application();

        ObjectMapper.mapObject(applicationDTO, application, true);
        logger.info(application.toString() + " AFTER MAPPING");
        try {
            applicationDAO.addApplication(application);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "While Applying", e);
        }
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
