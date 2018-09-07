package com.care.service;

import com.care.dto.form.ApplicationDTO;
import com.care.model.*;
import com.care.dao.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SitterServiceImpl extends MemberDAOImpl implements SitterService {

    private Logger logger = Logger.getLogger("SitterServiceImpl");

    public SitterServiceImpl(){ }

    public List<Job> listAllAvailableJobs(Member sitter, OperationStatus operationStatus) {
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

    public List<Application> listAllApplications(Member sitter, OperationStatus operationStatus) {
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
    public Job getJob(int jobId, OperationStatus operationStatus) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = Job.EMPTY_JOB;
        try {
            job = jobDAO.getJob(jobId);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "While getting a Job", e);
        }
        return job;
    }

    @Override
    public Sitter getSitter(int sitterId, OperationStatus operationStatus) {
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        Sitter sitter = Sitter.EMPTY_SITTER;

        try {
            sitter = sitterDAO.getSitter(sitterId);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "While fetching Sitter");
        }
        return sitter;
    }

    public OperationStatus applyToJob(ApplicationDTO applicationDTO) {

        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        Application application = new Application();
        OperationStatus operationStatus = OperationStatus.FAILURE;
        int val = -1;
        ObjectMapper.mapObject(applicationDTO, application, true);
        logger.info(application.toString() + " AFTER MAPPING");
        try {
            val = applicationDAO.addApplication(application);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "While Applying", e);
            operationStatus = OperationStatus.FAILURE;
        }

        if (val == 1){
            operationStatus = OperationStatus.SUCCESS;
        }
        return operationStatus;
    }

    public OperationStatus deleteApplication(Member sitter, int applicationId) {
        int val = 0;
        OperationStatus operationStatus = OperationStatus.FAILURE;
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        try {
            val = applicationDAO.setApplicationStatus(applicationId, );
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Error Closing application", e);
            operationStatus = OperationStatus.FAILURE;
        }
        if (val == 1){
            operationStatus = OperationStatus.SUCCESS;
        }
        return operationStatus;
    }
}
