package com.care.service;

import com.care.exception.InvalidApplicationException;
import com.care.exception.InvalidIdException;
import com.care.exception.JobNotPostedByUserException;
import com.care.model.*;
import com.care.dao.*;
import com.care.form.JobForm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        return seekers
                .stream()
                .sorted(Comparator.comparing(Seeker::getId))
                .collect(Collectors.toList());
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
            logger.log(Level.SEVERE, "While getting a job", e);
            throw new JobNotPostedByUserException(e);
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
        List<Job> memberJobs ;
        try {
            memberJobs = jobDAO.getAllJobs(member.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't access database", e);
            memberJobs = Collections.emptyList();
        }
        logger.info("Size of list-----" + memberJobs.size());
        return memberJobs
                .stream()
                .filter(Job::isActive)
                .sorted(Comparator.comparing(Job::getStartDateTime))
                .collect(Collectors.toList());
    }
    //[todo] throws InvalidIdException
    public List<Application> getApplications(Member member, long jobId) throws InvalidApplicationException {
        Set<Application> applications;
        logger.info("ListApplications");
        ApplicationDAO applicationDAO = DAOFactory.get(HApplicationDAOImpl.class);
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        logger.info(member + "MEMBER");
        try {
            if (verifyJobBelongsToMember(member, jobId)){
                applications = applicationDAO.getAllApplicationsOnJob(jobId);
                //check if job is Active.
            }else {
                throw new InvalidApplicationException();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't fetch All Applications on Job", e);

            throw new InvalidApplicationException();
        }
        return applications
                .stream()
                .sorted(Comparator.comparingDouble(Application::getExpectedPay))
                .collect(Collectors.toList());
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
    /*
    Can't edit an expired or a closed Job or a job not posted by him.
     */
    public OperationStatus editJob(Member member, JobForm jobForm) throws InvalidIdException{
        int status = 1;
        OperationStatus operationStatus = OperationStatus.SUCCESS;
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        Job job = new Job();
        try{
             if (verifyJobBelongsToMember(member, Long.valueOf(jobForm.getId()))){
                 job = jobDAO.getJob(Long.valueOf(jobForm.getId()));
                 ObjectMapper.mapObject(jobForm, job, true);
                 jobDAO.editJob(job);
             }else {
                 throw new InvalidIdException();
             }

        }catch (Exception e){
            logger.log(Level.SEVERE, "Getting a job", e);
            throw new InvalidIdException();
        }
        logger.info("------- " +status + "-------- ");
        return operationStatus;
    }

    /*
    Check if member is the owner.
     */
    public OperationStatus closeJob(Member member, long jobId) throws InvalidIdException {
        OperationStatus operationStatus = OperationStatus.SUCCESS;
        JobDAO jobDAO = DAOFactory.get(HJobDAOImpl.class);
        ApplicationDAO applicationDAO = DAOFactory.get(HApplicationDAOImpl.class);
        try{
            Job job = jobDAO.getJob(jobId);
            logger.info(job + " ");
            if (job.getSeeker().getId() == member.getId()){
                job.close();
            }else {
                operationStatus = OperationStatus.UNAUTHORISED;
                throw new  JobNotPostedByUserException("Can;t close job");
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Can't delete Job", e);
            operationStatus = OperationStatus.FAILURE;
            throw new InvalidIdException(e);
        }

        return operationStatus;
    }
}
