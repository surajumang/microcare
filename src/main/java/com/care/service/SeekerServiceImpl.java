package com.care.service;

import com.care.exception.*;
import com.care.model.*;
import com.care.dao.*;
import com.care.form.JobForm;

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
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
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
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
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

    /*
    Check if Job is Active or NOT, and also if it is posted by the given member or not.
     */
    public Job getJob(Member member, long jobId) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = Job.emptyJob();
        try {
                job = jobDAO.getJob(jobId);
                if (job.getSeeker().getId() != member.getId()){
                    throw new JobNotPostedByUserException();
                }
                if (!job.isActive()){
                    throw new JobExpiredException("Seeker fetching an Expired Job");
                }
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "While getting a job", e);
            throw new JobNotFoundException(e);
        }
        // There was a successful fetch from the DB.

        return job;
    }

    public OperationStatus postJob(Member member, JobForm jobForm)  {
        logger.info("Post job called by" + member.getId());
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);

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
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
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

    public List<Application> getApplications(Member member, long jobId) {
        Set<Application> applications;
        logger.info("ListApplications");
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        logger.info(member + "MEMBER");
        try {
            Job job = jobDAO.getJob(jobId);
            if (! verifyJobBelongsToMember(member, jobId)){
                throw new UnauthorizedJobAccessException("Can't get Applications for jobID " + jobId);
            }
            if (! job.isActive()){
                throw new JobExpiredException("Getting applications for jobId " + jobId);
            }
            applications = applicationDAO.getAllApplicationsOnJob(jobId);
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Can't fetch All Applications on JobID " + jobId, e);

            throw new ApplicationNotFoundException(e);
        }
        return applications
                .stream()
                .sorted(Comparator.comparingDouble(Application::getExpectedPay))
                .collect(Collectors.toList());
    }

    private boolean verifyJobBelongsToMember(Member member, long jobId){
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = jobDAO.getJob(jobId);
        logger.info(job + " ");
        return job.getSeeker().getId() == member.getId();
    }
    /*
    Can't edit an expired or a closed Job or a job not posted by him.
     */
    public OperationStatus editJob(Member member, JobForm jobForm) {
        int status = 1;
        OperationStatus operationStatus = OperationStatus.SUCCESS;
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = new Job();
        try{
             job = jobDAO.getJob(Long.valueOf(jobForm.getId()));
             if (!verifyJobBelongsToMember(member, Long.valueOf(jobForm.getId()))){
                throw new UnauthorizedJobAccessException("edit");
             }
             if (! job.isActive()){
                 throw new JobExpiredException("Can't edit expired job");
             }

             job = jobDAO.getJob(Long.valueOf(jobForm.getId()));
             ObjectMapper.mapObject(jobForm, job, true);
             jobDAO.editJob(job);

        }catch (DataReadException e){
            logger.log(Level.SEVERE, "Getting a job", e);
            throw new JobNotFoundException(e);
        }
        logger.info("------- " +status + "-------- ");
        return operationStatus;
    }

    /*
    Check if member is the owner.
     */
    public OperationStatus closeJob(Member member, long jobId) {
        OperationStatus operationStatus = OperationStatus.SUCCESS;
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        try{
            Job job = jobDAO.getJob(jobId);
            logger.info(job + " ");
            if (job.getSeeker().getId() != member.getId()){
                throw new UnauthorizedJobAccessException("Can't close jobId " + jobId);
            }
            if (! job.isActive()){
                throw new JobExpiredException("can't delete expired job ID " + jobId);
            }
            job.close();
        }catch (DataReadException e){
            logger.log(Level.SEVERE, "Can't delete Job ID " + jobId , e);
            throw new JobNotFoundException(e);
        }

        return operationStatus;
    }
}
