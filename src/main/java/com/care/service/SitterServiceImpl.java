package com.care.service;

import com.care.exception.*;
import com.care.form.ApplicationForm;
import com.care.model.*;
import com.care.dao.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SitterServiceImpl implements SitterService {

    private Logger logger = Logger.getLogger("SitterServiceImpl");

    public SitterServiceImpl(){ }

    public List<Job> listAllAvailableJobs(Member sitter) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> allJobs;

        try{
            allJobs = jobDAO.getAllAvailableJobs(sitter.getId());
        }catch (Exception e){
            logger.log(Level.SEVERE, "Getting all Available Jobs" , e);
            allJobs = Collections.emptyList();
        }
        return allJobs
                .stream()
                .filter(Job::isActive)
                .sorted(Comparator.comparing(Job::getHourlyPay).reversed()) //highest pay first.
                .collect(Collectors.toList());
    }
    /*
    Only the ACTIVE OR EXPIRED not closed.[todo] may throw some exception.
     */
    public List<Application> listAllApplications(Member sitter) {
        ApplicationDAO applicationDAO= DAOFactory.get(ApplicationDAOImpl.class);
        List<Application> applications ;
        try{
            applications = applicationDAO.getAllApplications(sitter.getId());
        }catch (Exception e){
            logger.log(Level.SEVERE , "All getApplications for Sitter", e);
            applications = Collections.emptyList();
        }
        return applications
                .stream()
                .filter(application -> ! application.isClosed())
                .sorted(Comparator.comparing(Application::getDateCreated))
                .collect(Collectors.toList());
    }

    /*
    Throw appropriate exception.JobNotFoundException
    should also have a Current Member reference and check if the User is the owner of the Exception.
    This is the method which gets called for the Sitter to get A job to apply on.
    This should not be EXPIRED OR CLOSED.
    Dao should either throw an Exception or give a Job from the DB, there shouldn't be anything related to
    EMPTY job.
     */
    public Job getJob(long sitterId, long jobId) {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        Job job = Job.emptyJob();
        try {
            Sitter sitter = sitterDAO.getSitter(sitterId);
            if (sitter.hasApplied(jobId)){
                throw new UnauthorizedJobAccessException("Already applied to job");
            }
            job = jobDAO.getJob(jobId);
            if (! job.isActive()){
                throw new JobExpiredException("Sitter can't apply to expired Job");
            }

        } catch (DataReadException e){
            logger.log(Level.SEVERE, "While getting a Job", e);
            throw new JobNotFoundException(e);
        }

        return job;
    }

    public Sitter getSitter(long sitterId) {
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        Sitter sitter = Sitter.emptySitter();
        try {
            sitter = sitterDAO.getSitter(sitterId);
        }catch (Exception e){
            logger.log(Level.SEVERE, "While fetching Sitter using ID", e);
        }
        return sitter;
    }

    /*
    Throw appropriate Exception[todo] SitterNotFoundException
     */
    public List<Sitter> getSittersByEmail(String email) {
        Set<Sitter> sitters = Collections.emptySet();
        logger.info("Fetching sitters by email" + email);
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        try {
            sitters = sitterDAO.getSitterByEmail(email);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Can't get Sitter using Email", e);
        }
        logger.info(" " + sitters);
        return sitters
                .stream()
                .filter(Sitter::isActive)
                .sorted(Comparator.comparing(Sitter::getExpectedPay))   //lowest expectedPay first.
                .collect(Collectors.toList());
    }


    public OperationStatus applyToJob(ApplicationForm applicationForm) {
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);

        Application application = new Application();
        OperationStatus operationStatus = OperationStatus.FAILURE;

        ObjectMapper.mapObject(applicationForm, application, true);
        logger.info(application + " AFTER MAPPING");
        try {
            Sitter sitter = sitterDAO.getSitter(Long.valueOf(applicationForm.getSitterId()));

            if (sitter.hasApplied(Long.valueOf(applicationForm.getJobId()))){
                throw new UnauthorizedJobAccessException("Already applied to job");
            }
            Job job = jobDAO.getJob(Long.valueOf(applicationForm.getJobId()));
            application.setJob(job);
            application.setSitter(sitter);

            if (applicationDAO.addApplication(application) == 1){
                operationStatus = OperationStatus.SUCCESS;
            }
        } catch (DataWriteException e) {
            logger.log(Level.SEVERE, "While Applying", e);
            throw e;
        }
        return operationStatus;
    }

    private boolean checkApplicationOwner(Member sitter, long applicationId) {
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        Application application = applicationDAO.getApplication(applicationId);
        return application.getSitter().getId() == sitter.getId();
    }
    // Move this logic to application Model[todo]
    public OperationStatus deleteApplication(Member sitter, long applicationId) {
        OperationStatus operationStatus = OperationStatus.SUCCESS;
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        logger.info("APPLICATion being deleted : " + applicationId + "BYY" + sitter.getId());
        try {
            Application application = applicationDAO.getApplication(applicationId);
            if (application.getSitter().getId() != sitter.getId()){
                new UnauthorizedApplicationAccessException("While deleting");
            }
            if (application.isClosed()){
                throw new ExpiredApplicationException("While deleting");
            }
            application.close();

        }catch (DataReadException e){
            logger.log(Level.SEVERE, "Error Closing application", e);
            throw new ApplicationNotFoundException(e);
        }
        return operationStatus;
    }
}
