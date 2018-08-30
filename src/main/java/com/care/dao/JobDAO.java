package com.care.dao;

import com.care.beans.Job;

import java.sql.SQLException;
import java.util.List;

public interface JobDAO extends DAO {
    int addJob(Job job) throws SQLException;
    // only owners will be able to edit or delete jobs.
    boolean checkOwner(int postedBy, int jobId) throws SQLException;
    // deletion is nothing but marking the status flag as INACTIVE
    int deleteJob(int jobId) throws SQLException;
    int deleteAllJobs(int postedBy) throws SQLException;
    int editJob(int jobId) throws SQLException;
    Job getJob(int jobId) throws SQLException;
    List<Job> getAllJobs(int postedBy) throws SQLException;

}
