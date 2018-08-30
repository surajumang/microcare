/*

*/
package com.care.dao;

import com.care.beans.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class JobDAOImpl implements JobDAO {

    private static JobDAOImpl ourInstance = new JobDAOImpl();
    private Connection connection = ConnectionUtil.getConnection();

    public static <T extends DAO> T getInstance(){
        return (T)ourInstance;
    }
    private JobDAOImpl(){

    }

    public Job getJob(int jobId) throws SQLException {
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
    public List<Job> getAllJobs(int memberId) throws SQLException {
        List<Job> jobs = new ArrayList<Job>();
        jobs.add(new Job());

        return jobs;
    }
    public int deleteJob(int jobId) throws SQLException {
        return 0;
    }

    public int deleteAllJobs(int postedBy) throws SQLException {
        return 0;
    }
    
}
