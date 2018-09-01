package com.care.service;

import com.care.beans.Job;
import com.care.dao.DAOFactory;
import com.care.dao.JobDAO;
import com.care.dao.JobDAOImpl;
import com.care.dto.form.ApplicationDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SitterServiceImpl implements SitterService {

    private Logger logger = Logger.getLogger("SitterServiceImpl");
    private static SitterServiceImpl ourInstance = new SitterServiceImpl();

    public static SitterServiceImpl getInstance(){
        return ourInstance;
    }

    private SitterServiceImpl(){

    }

    public List<Job> listAllJobs() {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> allJobs = new ArrayList<Job>();

        try{
            allJobs = jobDAO.getAllJobs();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Getting all Jobs" , e);
        }


        return allJobs;
    }

    public List<ApplicationDTO> listAllApplications(int memberId) {
        return null;
    }

    public List<ApplicationDTO> listApplication(int applicationId) {
        return null;
    }

    public int applyToJob(int jobId) {
        return 0;
    }

    public int deleteApplication(int applicationId) {
        return 0;
    }
}
