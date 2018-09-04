package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class SeekerDAOImpl implements SeekerDAO {
    private Logger logger = Logger.getLogger("SeekerDAOImpl");

    public SeekerDAOImpl(){ }

    public int postJob(Job job) throws SQLException {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        return jobDAO.addJob(job);
    }

    public int editJob(Job job) throws SQLException {
        return postJob(job);
    }
    /*
    Check if the job to be deleted belongs to the currently logged in Sitter.
     */
    public int deleteJob(int jobId) throws SQLException {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        return jobDAO.deleteJob(jobId);
    }

    public List<Job> listAllMyJobs(int postedBy) throws SQLException {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        return jobDAO.getAllJobs(postedBy);
    }

    public List<Application> listAllApplicationsOnMyJob(int jobId) throws SQLException {
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);

        //return applicationDAO.getAllApplicationsOnJob(jobId);
        return null;
    }

    public int closeJob(int jobId) throws SQLException {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        return jobDAO.deleteJob(jobId);
    }
}
