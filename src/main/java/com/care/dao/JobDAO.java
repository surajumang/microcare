package com.care.dao;

import com.care.model.Job;
import com.care.model.Status;

import java.sql.SQLException;
import java.util.List;

public interface JobDAO extends DAO {
    int addJob(Job job) throws SQLException;

    // deletion is nothing but marking the status flag as INACTIVE
    int setJobStatus(int jobId, Status status) throws SQLException;

    int setAllJobsStatus(int postedBy, Status status) throws SQLException;

    int editJob(Job jobId) throws SQLException;

    Job getJob(int jobId) throws SQLException;

    List<Job> getAllJobs(int postedBy) throws SQLException;

    List<Job> getAllAvailableJobs(int sitterId)throws SQLException;

}
