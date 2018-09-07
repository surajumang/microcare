package com.care.service;

import com.care.model.*;
import com.care.dao.*;
import com.care.dto.form.JobDTO;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerServiceImpl implements SeekerService {

    Logger logger = Logger.getLogger("SeekerServiceImpl");

    public SeekerServiceImpl(){    }

    public Seeker getSeeker(int seekerId, OperationStatus operationStatus) {
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        Seeker seeker = Seeker.EMPTY_SEEKER;
        try{
            seekerDAO.getSeeker(seekerId);
        }catch(SQLException e){
            logger.log(Level.SEVERE, "getting seeker", e);
        }
        return seeker;
    }

    @Override
    public Job getJob(Member member, int jobId, OperationStatus operationStatus) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = Job.EMPTY_JOB;
        operationStatus = OperationStatus.SUCCESS;

        try {
            if (verifyJobBelongsToMember(member, jobId)){
                job = jobDAO.getJob(jobId);
            }else {
                operationStatus = OperationStatus.UNAUTHORISED;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "exception Getting a single job", e);
            operationStatus = OperationStatus.FAILURE;
        }
        return job;
    }

    public OperationStatus postJob(Member member, JobDTO jobForm)  {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = new Job();
        OperationStatus status = OperationStatus.SUCCESS;
        int val = -1;
        ObjectMapper.mapObject(jobForm, job, true);

        try{
            val = jobDAO.addJob(job);

        }catch (SQLException e){
            logger.log(Level.SEVERE, "Posting Job", e);
            status = OperationStatus.FAILURE;
        }

        if (val != 1){
            status = OperationStatus.FAILURE;
        }

        return status;
    }

    public List<Job> listJobs(Member member, OperationStatus operationStatus) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> memberJobs ;
        operationStatus = OperationStatus.SUCCESS;
        try {
            memberJobs = jobDAO.getAllJobs(member.getId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't access database", e);
            memberJobs = Collections.emptyList();
            operationStatus = OperationStatus.FAILURE;
        }
        logger.info(operationStatus.name() + "OPERATION STATUS FOR lIST JOB");
        logger.info("Size of list-----" + memberJobs.size());
        return memberJobs;
    }

    public List<Application> getApplications(Member member, int jobId, OperationStatus operationStatus) {

        List<Application> applications = Collections.emptyList();
        logger.info("ListApplications");

        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        operationStatus = OperationStatus.SUCCESS;
        logger.info(member + "MEMBER");

        try {
            if (verifyJobBelongsToMember(member, jobId)){
                applications = applicationDAO.getAllApplicationsOnJob(jobId);
            }else {
                operationStatus = OperationStatus.UNAUTHORISED;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't fetch All Applications on Job", e);
            applications = Collections.emptyList();
            operationStatus = OperationStatus.FAILURE;
        }
        return applications;
    }

    private boolean verifyJobBelongsToMember(Member member, int jobId){
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        boolean status = true;
        try{
            Job job = jobDAO.getJob(jobId);
            logger.info(job + " ");
            status = job.getSeekerId() == member.getId();
        }catch (SQLException e){
            status = false;
        }
        return status;
    }

    public OperationStatus editJob(Member member, JobDTO jobForm) {
        int status = 1;
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = new Job();
        OperationStatus operationStatus = OperationStatus.SUCCESS;
        ObjectMapper.mapObject(jobForm, job, true);

        try{
            status = jobDAO.editJob(job);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Can't edit a Job", e);
            operationStatus = OperationStatus.FAILURE;
        }
        logger.info("------- " +status + "-------- ");
        if (status != 1){
            operationStatus=OperationStatus.FAILURE;
        }

        return operationStatus;
    }

    /*
    Check if member is the owner.
     */
    public OperationStatus closeJob(Member member, int jobId)  {
        OperationStatus operationStatus = OperationStatus.SUCCESS;
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        int status = -1;
        try{
            if (verifyJobBelongsToMember(member, jobId)){
                status = jobDAO.setJobStatus(jobId, Status.CLOSED);
                applicationDAO.setAllApplicationStatusByJob(jobId, Status.EXPIRED);
            }else {
                operationStatus = OperationStatus.UNAUTHORISED;
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Can't delete Job", e);
            operationStatus = OperationStatus.FAILURE;
        }
        //logger.info("------- " +status + "-------- ");
        if (status != 1){
            operationStatus = OperationStatus.FAILURE;
        }

        return operationStatus;
    }
}
