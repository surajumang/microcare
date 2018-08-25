/*

*/
package com.care.dao;

import com.care.beans.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class JobDAO {
    private JobDAO(){

    }

    public static Job getJob(int jobId, Connection connection){
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
    public static void postJob(Job job, Connection connection){

    }

    public static void editJob(Job newJob, Connection connection){

    }
    public static List<Job> getAllJobs(int memberId, Connection connection){
        List<Job> jobs = new ArrayList<Job>();
        jobs.add(new Job());

        return jobs;
    }
    public static void deleteJob(int jobId, Connection connection){

    }

    public static void deleteAllJobs(int memberId, Connection connection){

    }
    
}
