package com.care.service;

import com.care.form.ApplicationForm;
import com.care.model.*;
import com.care.dao.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SitterServiceImpl implements SitterService {

    private Logger logger = Logger.getLogger("SitterServiceImpl");

    public SitterServiceImpl(){ }

    public List<Job> listAllAvailableJobs(Member sitter) {
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        Set<Job> allJobs;

        try{
            allJobs = jobDAO.getAllAvailableJobs(sitter.getId());
        }catch (Exception e){
            logger.log(Level.SEVERE, "Getting all Available Jobs" , e);
            allJobs = Collections.emptySet();
        }
        return new ArrayList<>(allJobs);
    }
    /*
    Only the ACTIVE OR EXPIRED not closed.
     */
    public List<Application> listAllApplications(Member sitter) {
        ApplicationDAO applicationDAO= DAOFactory.get(HApplicationDAOImpl.class);
        Set<Application> applications ;
        try{
            applications = applicationDAO.getAllApplications(sitter.getId());
        }catch (Exception e){
            logger.log(Level.SEVERE , "All getApplications for Sitter", e);
            applications = Collections.emptySet();
        }
        return new ArrayList<>(applications);
    }

    public Job getJob(long jobId) {
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        Job job = Job.emptyJob();
        try {
            job = jobDAO.getJob(jobId);
        } catch (Exception e){
            logger.log(Level.SEVERE, "While getting a Job", e);
        }
        return job;
    }

    public Sitter getSitter(long sitterId) {
        SitterDAO sitterDAO = DAOFactory.get(HSitterDAOImpl.class);
        Sitter sitter = Sitter.emptySitter();
        try {
            sitter = sitterDAO.getSitter(sitterId);
        }catch (Exception e){
            logger.log(Level.SEVERE, "While fetching Sitter", e);
        }
        return sitter;
    }

    public List<Sitter> getSittersByEmail(String email) {
        Set<Sitter> sitters = Collections.emptySet();
        logger.info("Fetching sitters by email" + email);
        SitterDAO sitterDAO = DAOFactory.get(HSitterDAOImpl.class);
        try {
            sitters = sitterDAO.getSitterByEmail(email);
        }catch (Exception e){
            logger.log(Level.SEVERE, "ads", e);
        }
        logger.info(" " + sitters);
        return new ArrayList<>(sitters);
    }


    public OperationStatus applyToJob(ApplicationForm applicationForm) {
        ApplicationDAO applicationDAO = DAOFactory.get(HApplicationDAOImpl.class);
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        SitterDAO sitterDAO = DAOFactory.get(HSitterDAOImpl.class);

        Application application = new Application();
        OperationStatus operationStatus = OperationStatus.FAILURE;

        ObjectMapper.mapObject(applicationForm, application, true);
        logger.info(application + " AFTER MAPPING");
        try {
            /*
            Get Job and sitter refrences before saving.
             */
            Job job = jobDAO.getJob(Long.valueOf(applicationForm.getJobId()));
            Sitter sitter = sitterDAO.getSitter(Long.valueOf(applicationForm.getSitterId()));

            application.setJob(job);
            application.setSitter(sitter);
            int val = applicationDAO.addApplication(application);

            if (val == 1){
                operationStatus = OperationStatus.SUCCESS;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "While Applying", e);
            operationStatus = OperationStatus.FAILURE;
        }
        return operationStatus;
    }

    public OperationStatus deleteApplication(Member sitter, long applicationId) {

        OperationStatus operationStatus = OperationStatus.FAILURE;
        ApplicationDAO applicationDAO = DAOFactory.get(HApplicationDAOImpl.class);
        logger.info("APPLICATion being deleted : " + applicationId + "BYY" + sitter.getId());
        try {
            int val = applicationDAO.setApplicationStatus(applicationId,Status.CLOSED );
            if (val == 1){
                operationStatus = OperationStatus.SUCCESS;
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error Closing application", e);
            operationStatus = OperationStatus.FAILURE;
        }
        return operationStatus;
    }
}
