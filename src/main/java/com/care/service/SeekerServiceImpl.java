package com.care.service;

import com.care.exception.IllegalApplicationAccessException;
import com.care.exception.JobNotPostedByUserException;
import com.care.model.*;
import com.care.dao.*;
import com.care.dto.form.JobDTO;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerServiceImpl implements SeekerService {

    private Logger logger = Logger.getLogger("SeekerServiceImpl");

    public SeekerServiceImpl(){    }

    public Seeker getSeeker(long seekerId) {
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        Seeker seeker = Seeker.EMPTY_SEEKER;
        try{
            seeker = seekerDAO.getSeeker(seekerId);
        }catch(SQLException e){
            logger.log(Level.SEVERE, "getting seeker", e);
        }
        return seeker;
    }

    public List<Seeker> getSeekerByEmail(String email) {
        logger.info("Fetching seekers by Email");
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        List<Seeker> seekers = Collections.emptyList();
        try {
            seekers = seekerDAO.getSeekerByEmail(email);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "fs", e);
        }
        logger.info(seekers.size() + " ");
        return seekers;
    }


    public Job getJob(Member member, long jobId) throws JobNotPostedByUserException {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = Job.EMPTY_JOB;
        try {
            if (verifyJobBelongsToMember(member, jobId)){
                job = jobDAO.getJob(jobId);
            }else {
                throw new JobNotPostedByUserException("INvalid request to get Job");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "exception Getting a single job", e);
        }
        return job;
    }

    public OperationStatus postJob(Member member, JobDTO jobForm)  {
        logger.info("Post job called by" + member.getId());
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = new Job();
        OperationStatus status = OperationStatus.SUCCESS;
        int val = -1;
        ObjectMapper.mapObject(jobForm, job, true);

        logger.info("After mapping " + job);
        try{
            val = jobDAO.addJob(job);
            if (val != 1){
                status = OperationStatus.FAILURE;
                logger.info("PostJob failed");
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Posting Job", e);
            status = OperationStatus.FAILURE;
        }



        return status;
    }

    public List<Job> listJobs(Member member) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> memberJobs ;
        try {
            memberJobs = jobDAO.getAllJobs(member.getId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't access database", e);
            memberJobs = Collections.emptyList();

        }
        logger.info("Size of list-----" + memberJobs.size());
        return memberJobs;
    }

    public List<Application> getApplications(Member member, long jobId) throws IllegalApplicationAccessException {
        List<Application> applications;
        logger.info("ListApplications");

        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        logger.info(member + "MEMBER");
        try {
            if (verifyJobBelongsToMember(member, jobId)){
                applications = applicationDAO.getAllApplicationsOnJob(jobId);
            }else {
                throw new IllegalApplicationAccessException();
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't fetch All Applications on Job", e);
            applications = Collections.emptyList();
        }
        return applications;
    }

    private boolean verifyJobBelongsToMember(Member member, long jobId){
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        boolean status = false;
        try{
            Job job = jobDAO.getJob(jobId);
            logger.info(job + " ");
            status = job.getSeekerId() == member.getId();
        }catch (SQLException e){
            status = false;
        }
        return status;
    }

    public OperationStatus editJob(Member member, JobDTO jobForm){
        int status = 1;
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = new Job();
        OperationStatus operationStatus = OperationStatus.SUCCESS;

        ObjectMapper.mapObject(jobForm, job, true);
        logger.info(job + "JOB TO BE EDITED");
        try{
            status = jobDAO.editJob(job);
            logger.info(status + " ");
            if (status != 1){
                logger.info("Couldn't edit job");
                operationStatus=OperationStatus.FAILURE;
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Can't edit a Job", e);
            operationStatus = OperationStatus.FAILURE;
        }
        logger.info("------- " +status + "-------- ");
        return operationStatus;
    }

    /*
    Check if member is the owner.
     */
    public OperationStatus closeJob(Member member, long jobId) throws JobNotPostedByUserException {
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
                throw new  JobNotPostedByUserException("Can;t close job");
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
