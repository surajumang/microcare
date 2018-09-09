/*

*/
package com.care.dao;

import com.care.model.Job;
import com.care.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class JobDAOImpl implements JobDAO {

    private Logger logger = Logger.getLogger("JobDAOImpl");

    public JobDAOImpl(){ }

    public Job getJob(long jobId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Get job method");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM JOB WHERE ID=?");
        statement.setLong(1,jobId);
        ResultSet resultSet = statement.executeQuery();
        Job job = null;

        if (resultSet.next()){
            job = populateJob(resultSet);
            logger.info(job + " ");
        }
        return job;
    }
    /*
    Post a job
     */
    public int addJob(Job job) throws SQLException {
        logger.info("Adding a Job" + job);
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO JOB(TITLE, POSTED_BY, HOURLY_PAY, START_DATE, " +
                "END_DATE) VALUES (?, ?, ?, ?, ? )");
        statement.setString(1, job.getTitle());
        statement.setLong(2, job.getSeekerId());
        statement.setDouble(3, job.getHourlyPay());
        statement.setTimestamp(4, job.getStartDate());
        statement.setTimestamp(5, job.getEndDate());

        return statement.executeUpdate();
    }

    public int setJobStatus(long jobId, Status status) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info(jobId + "***********");
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET STATUS=? WHERE ID = ?");
        statement.setString(1, status.name());
        statement.setLong(2, jobId);

        return statement.executeUpdate();
    }

    public int editJob(Job job) throws SQLException {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET TITLE = ?, START_DATE=?, END_DATE=?, HOURLY_PAY=? WHERE ID = ?");
        statement.setString(1, job.getTitle());
        statement.setTimestamp(2, job.getStartDate());
        statement.setTimestamp(3, job.getEndDate());
        statement.setDouble(4, job.getHourlyPay());
        statement.setLong(5, job.getId());

        return statement.executeUpdate();
    }

    /*
    [todo]
    This is for Seeker to get all jobs posted by him,
     */
    public List<Job> getAllJobs(long postedBy) throws SQLException {
        logger.info("GetAllJobs Seeker");
        List<Job> jobs = new ArrayList<Job>();
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT ID, TITLE, STATUS, START_DATE, END_DATE, HOURLY_PAY, POSTED_BY " +
                "FROM JOB " +
                "WHERE POSTED_BY = ? AND STATUS <> ?");
        statement.setLong(1, postedBy);
        statement.setString(2, Status.CLOSED.name());

        logger.info(postedBy + " User ID to createObject job from DB");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            logger.info("Picked one row from the result set");
            jobs.add(populateJob(resultSet));
        }
        return jobs;
    }
    /*
    All active jobs only. and the ones not applied by the sitter.
    This will be called by Sitter.
     */
    public List<Job> getAllAvailableJobs(long sitterId) throws SQLException {
        List<Job> allAvailableJobs = new ArrayList<Job>();
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT STATUS, ID,POSTED_BY, TITLE, HOURLY_PAY, START_DATE, END_DATE FROM JOB WHERE STATUS = ? AND JOB.ID NOT IN (SELECT APPLICATION.JOB_ID FROM APPLICATION WHERE SITTER_ID = ? AND APPLICATION.STATUS = 'ACTIVE' )");
        statement.setString(1, Status.ACTIVE.name());
        statement.setLong(2, sitterId);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            logger.info("Picked one row from the result set");
            allAvailableJobs.add(populateJob(resultSet));
        }
        return allAvailableJobs;
    }


    public int setAllJobsStatus(long postedBy, Status status) throws SQLException {
        logger.info("Close Seeker's Job");
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Acquired connection");
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET STATUS=?" +
                "WHERE POSTED_BY=?");
        logger.info( " JobId ID to create job from DB" + postedBy);
        statement.setString(1, status.name());
        statement.setLong(2, postedBy);
        return statement.executeUpdate();
    }

    @Override
    public int expireStaleJobs() throws SQLException {
        logger.info("Expiring stale jobs ");
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE JOB SET STATUS=? WHERE START_DATE < ? AND STATUS <> ? " );

        statement.setString(1, Status.EXPIRED.name());
        statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        statement.setString(3, Status.CLOSED.name());
        return statement.executeUpdate();
    }

    private Job populateJob(ResultSet resultSet) throws SQLException{
        Job job = new Job();

        job.setTitle(resultSet.getString("TITLE"));
        job.setId(resultSet.getLong("ID"));
        job.setSeekerId(resultSet.getLong("POSTED_BY"));
        job.setHourlyPay(resultSet.getDouble("HOURLY_PAY"));
        job.setStartDate(resultSet.getTimestamp("START_DATE"));
        job.setEndDate(resultSet.getTimestamp("END_DATE"));
        job.setStatus(Status.valueOf(resultSet.getString("STATUS")));

        logger.info("Added one job successfully " + job);
        return job;
    }
}
