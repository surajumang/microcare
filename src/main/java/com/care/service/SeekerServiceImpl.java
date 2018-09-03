package com.care.service;

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

    private static SeekerServiceImpl ourInstance = new SeekerServiceImpl();
    public static SeekerServiceImpl getInstance(){
        return ourInstance;
    }

    private SeekerServiceImpl(){

    }

    public int postJob(Member member, JobDTO jobForm)  {
        return 0;
    }


    public List<Job> listJobs(Member member) {

        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> memberJobs = new ArrayList<Job>();

        try {
            memberJobs = jobDAO.getAllJobs(member.getId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't access database", e.getCause());
            memberJobs = Collections.emptyList();
        }

        return memberJobs;
    }

    public List<ApplicationDTO> listApplicationsOnJob(Member member, int jobId) {

        List<ApplicationDTO> applicationDTOList = new ArrayList<ApplicationDTO>();
        logger.info("ListApplications");
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);

        try {
            applicationDTOList = applicationDAO.getAllApplicationsOnJob(jobId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't fetch All Applications on Job");
            applicationDTOList = Collections.emptyList();
        }
        return applicationDTOList;
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
            status = jobDAO.deleteJob(member, jobId);
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Can't delete Job", e);
            status = 0;
        }
        logger.info("------- " +status + "-------- ");

        return status;
    }
}
