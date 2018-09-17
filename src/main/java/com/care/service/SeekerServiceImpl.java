package com.care.service;

import com.care.exception.IllegalApplicationAccessException;
import com.care.exception.JobNotPostedByUserException;
import com.care.model.*;
import com.care.dao.*;
import com.care.form.JobForm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerServiceImpl implements SeekerService {

    private Logger logger = Logger.getLogger("SeekerServiceImpl");

    public SeekerServiceImpl(){    }

    public Seeker getSeeker(long seekerId) {
        SeekerDAO seekerDAO = DAOFactory.get(HSeekerDAOImpl.class);
        Seeker seeker = Seeker.emptySeeker();
        try{
            seeker = seekerDAO.getSeeker(seekerId);
        }catch(Exception e){
            logger.log(Level.SEVERE, "getting seeker", e);
        }
        return seeker;
    }

    public List<Seeker> getSeekersByEmail(String email) {
        logger.info("Fetching seekers by Email");
        SeekerDAO seekerDAO = DAOFactory.get(HSeekerDAOImpl.class);
        Set<Seeker> seekers = Collections.emptySet();
        try {
            seekers = seekerDAO.getSeekerByEmail(email);
        }catch (Exception e){
            logger.log(Level.SEVERE, "fs", e);
        }
        logger.info(seekers.size() + " ");
        return new ArrayList<>(seekers);
    }


    public Job getJob(Member member, long jobId) throws JobNotPostedByUserException {
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        Job job = Job.emptyJob();
        try {
            if (verifyJobBelongsToMember(member, jobId)){
                job = jobDAO.getJob(jobId);
            }else {
                throw new JobNotPostedByUserException("INvalid request to get Job");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "exception Getting a single job", e);
        }
        return job;
    }

    public OperationStatus postJob(Member member, JobForm jobForm)  {
        logger.info("Post job called by" + member.getId());
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        SeekerDAO seekerDAO = DAOFactory.get(HSeekerDAOImpl.class);

        Job job = new Job();
        OperationStatus status = OperationStatus.SUCCESS;
        int val = -1;
        ObjectMapper.mapObject(jobForm, job, true);

        logger.info("After mapping " + job);
        try{
            Seeker seeker = seekerDAO.getSeeker(member.getId());
            job.setStatus(Status.ACTIVE);
            job.setSeeker(seeker);

            val = jobDAO.addJob(job);
            if (val != 1){
                status = OperationStatus.FAILURE;
                logger.info("PostJob failed");
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Posting Job", e);
            status = OperationStatus.FAILURE;
        }

        return status;
    }

    public List<Job> listJobs(Member member) {
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        Set<Job> memberJobs ;
        try {
            memberJobs = jobDAO.getAllJobs(member.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't access database", e);
            memberJobs = Collections.emptySet();

        }
        logger.info("Size of list-----" + memberJobs.size());
        return new ArrayList<>(memberJobs);
    }

    public List<Application> getApplications(Member member, long jobId) throws IllegalApplicationAccessException {
        Set<Application> applications;
        logger.info("ListApplications");

        ApplicationDAO applicationDAO = DAOFactory.get(HApplicationDAOImpl.class);
        logger.info(member + "MEMBER");
        try {
            if (verifyJobBelongsToMember(member, jobId)){
                applications = applicationDAO.getAllApplicationsOnJob(jobId);
            }else {
                throw new IllegalApplicationAccessException();
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't fetch All Applications on Job", e);
            applications = Collections.emptySet();
        }
        return new ArrayList<>(applications);
    }

    private boolean verifyJobBelongsToMember(Member member, long jobId){
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        boolean status = false;
        try{
            Job job = jobDAO.getJob(jobId);
            logger.info(job + " ");
            //[todo] check if job is not null
            status = job.getSeeker().getId() == member.getId();
        }catch (Exception e){
            status = false;
        }
        return status;
    }

    public OperationStatus editJob(Member member, JobForm jobForm){
        int status = 1;
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
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
        }catch (Exception e){
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
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        ApplicationDAO applicationDAO = DAOFactory.get(HApplicationDAOImpl.class);
        int status = -1;
        try{
            if (verifyJobBelongsToMember(member, jobId)){
                status = jobDAO.setJobStatus(jobId, Status.CLOSED);
                applicationDAO.setAllApplicationStatusByJob(jobId, Status.EXPIRED);
            }else {
                operationStatus = OperationStatus.UNAUTHORISED;
                throw new  JobNotPostedByUserException("Can;t close job");
            }
        }catch (Exception e){
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
