/*

*/
package com.care.dao;

import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class JobDAOImpl implements JobDAO {

    private Logger logger = Logger.getLogger("JobDAOImpl");

    public JobDAOImpl(){ }

    public Job getJob(Member member, int jobId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();

        logger.info("Get job method");
        Job job = new Job();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION " +
                    "WHERE ID=?");
            statement.setInt(1,jobId);
            ResultSet resultSet = statement.executeQuery();


        }catch (SQLException e){
            e.getErrorCode();
        }
        return job;
    }
    /*
    Post a job
     */
    public int addJob(Job job) throws SQLException {
        return 0;
    }

    public boolean checkOwner(int postedBy, int jobId) throws SQLException {
        return false;
    }

    public int editJob(int jobId) throws SQLException {
        return 0;
    }
    public List<Job> getAllJobs(int postedBy) throws SQLException {
        logger.info("GetAllJobs Seeker");

        List<Job> jobs = new ArrayList<Job>();
        Connection connection = ConnectionUtil.getConnection();

        logger.info("Acquired connection");
        PreparedStatement statement = connection.prepareStatement("SELECT ID, TITLE, STATUS, START_DATE, END_DATE FROM JOB " +
                "WHERE POSTED_BY = ?");
        statement.setInt(1, postedBy);
        logger.info(postedBy + " User ID to create job from DB");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            logger.info("Picked one row from the result set");
            Job job = new Job();
            job.setTitle(resultSet.getString("TITLE"));
            job.setId(resultSet.getInt("ID"));
            job.setStartDate(resultSet.getDate("START_DATE"));
            job.setEndDate(resultSet.getDate("END_DATE"));
            job.setStatus(Status.valueOf(resultSet.getString("STATUS")));

            jobs.add(job);
        }

        return jobs;
    }
    //All active jobs only. and the ones not applied by the sitter.
    public List<Job> getAllJobs() throws SQLException {
        List<Job> allJobs = new ArrayList<Job>();
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM JOB WHERE STATUS = ?");
        statement.setString(1, Status.ACTIVE.name());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            logger.info("Picked one row from the result set");
            Job job = new Job();
            job.setTitle(resultSet.getString("TITLE"));
            job.setId(resultSet.getInt("ID"));
            job.setHourlyPay(resultSet.getDouble("HOURLY_PAY"));
            job.setStartDate(resultSet.getDate("START_DATE"));
            job.setEndDate(resultSet.getDate("END_DATE"));
            job.setStatus(Status.valueOf(resultSet.getString("STATUS")));

            allJobs.add(job);
        }
        return allJobs;
    }

    public int deleteJob(Member member, int jobId) throws SQLException {
        logger.info("Close Seeker's Job");

        Connection connection = ConnectionUtil.getConnection();

        logger.info("Acquired connection");
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET STATUS='INACTIVE'" +
                "WHERE ID=? AND POSTED_BY=?");

        logger.info(jobId + " JobId ID to create job from DB" + member.getId());
        statement.setInt(1, jobId);
        statement.setInt(2, member.getId());


        return statement.executeUpdate();

    }

    public int deleteAllJobs(Member member) throws SQLException {
        logger.info("Close Seeker's Job");

        Connection connection = ConnectionUtil.getConnection();

        logger.info("Acquired connection");
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET STATUS='INACTIVE'" +
                "WHERE POSTED_BY=?");

        logger.info( " JobId ID to create job from DB" + member.getId());
        statement.setInt(1, member.getId());


        return statement.executeUpdate();
    }
    
}
