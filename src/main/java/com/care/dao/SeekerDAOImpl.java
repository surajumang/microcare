package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.service.MemberService;
import com.care.service.MemberServiceImpl;
import com.care.service.ServiceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeekerDAOImpl implements SeekerDAO {

    private MemberService memberService = ServiceFactory.get(MemberServiceImpl.class);
    private Logger logger = Logger.getLogger("SeekerDAOImpl");
    private static SeekerDAOImpl ourInstance = new SeekerDAOImpl();
    public static SeekerDAOImpl getInstance(){
        return ourInstance;
    }

    private SeekerDAOImpl(){

    }

    public boolean postJob(Job job) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO JOB(TITLE, POSTED_BY, HOURLY_PAY, START_DATE, END_DATE)" +
                    "VALUES(?,?,?,?,?)");
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Posting Job", e.getCause());
            throw e;
        }
        return false;
    }

    public int editJob(int jobId, Job job) throws SQLException {
        return 0;
    }

    public boolean deleteJob(int jobId) throws SQLException {
        return false;
    }

    public List<Job> listAllMyJobs(int memberId) throws SQLException {
        return null;
    }

    public List<Application> listAllApplicationsOnMyJob(int jobId) throws SQLException {
        return null;
    }

    public boolean closeJob(int jobId) throws SQLException {
        return false;
    }
}
