package com.care.service;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;
import com.care.dao.*;
import com.care.dto.form.ApplicationDTO;
import com.care.dto.form.JobDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerServiceImpl implements SeekerService {

    Logger logger = Logger.getLogger("SeekerServiceImpl");

    public SeekerServiceImpl(){    }

    public boolean getSeeker() {
        return false;
    }

    public int postJob(Member member, JobDTO jobForm)  {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        Job job = new Job();
        int status;

        ObjectMapper.mapObject(jobForm, job, true);
        try{
            status = jobDAO.addJob(job);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Posting Job", e);
            status = -1;
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

        return memberJobs;
    }

    public List<Application> getApplications(Member member, int jobId) {

        List<Application> applications;
        logger.info("ListApplications");
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);

        try {
            applications = applicationDAO.getAllApplicationsOnJob(jobId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't fetch All Applications on Job", e);
            applications = Collections.emptyList();
        }
        return applications;
    }
    /*

     */
    private boolean verifyJobBelongsToMember(int jobId){

        return false;
    }

    public int editJob(Member member, JobDTO jobForm) {
        return 0;
    }

    public int closeJob(Member member, int jobId)  {
        int status = 1;

        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        try{
            status = jobDAO.deleteJob(jobId);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Can't delete Job", e);
            status = 0;
        }
        logger.info("------- " +status + "-------- ");

        return status;
    }
}
