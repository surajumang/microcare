package com.care.service;

import com.care.form.ApplicationForm;
import com.care.model.*;
import com.care.dao.*;

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

    public Job getJob(long jobId) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = Job.EMPTY_JOB;
        try {
            job = jobDAO.getJob(jobId);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "While getting a Job", e);
        }
        return job;
    }

    public Sitter getSitter(long sitterId) {
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        Sitter sitter = Sitter.EMPTY_SITTER;
        try {
            sitter = sitterDAO.getSitter(sitterId);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "While fetching Sitter", e);
        }
        return sitter;
    }

    public List<Sitter> getSitterByEmail(String email) {
        List<Sitter> sitters = Collections.emptyList();
        logger.info("Fetching sitters by email" + email);
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        try {
            sitters = sitterDAO.getSitterByEmail(email);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "ads", e);
        }
        logger.info(" " + sitters);
        return sitters;
    }


    public OperationStatus applyToJob(ApplicationForm applicationForm) {
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        Application application = new Application();
        OperationStatus operationStatus = OperationStatus.FAILURE;

        ObjectMapper.mapObject(applicationForm, application, true);
        logger.info(application + " AFTER MAPPING");
        try {
            int val = applicationDAO.addApplication(application);
            if (val == 1){
                operationStatus = OperationStatus.SUCCESS;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "While Applying", e);
            operationStatus = OperationStatus.FAILURE;
        }
        return operationStatus;
    }

    public OperationStatus deleteApplication(Member sitter, long applicationId) {

        OperationStatus operationStatus = OperationStatus.FAILURE;
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        logger.info("APPLICATion being deleted : " + applicationId + "BYY" + sitter.getId());
        try {
            int val = applicationDAO.setApplicationStatus(applicationId,Status.CLOSED );
            if (val == 1){
                operationStatus = OperationStatus.SUCCESS;
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Error Closing application", e);
            operationStatus = OperationStatus.FAILURE;
        }
        return operationStatus;
    }
}
