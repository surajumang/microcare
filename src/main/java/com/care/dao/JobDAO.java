package com.care.dao;

import com.care.model.Job;
import com.care.model.Status;

import java.sql.SQLException;
import java.util.Set;

public interface JobDAO extends DAO {
    int addJob(Job job) throws Exception;

    int setJobStatus(long jobId, Status status) throws Exception;

    int setAllJobsStatus(long postedBy, Status status) throws Exception;

    int expireStaleJobs() throws Exception;

    int editJob(Job job) throws Exception;

    Job getJob(long jobId) throws Exception;

    Set<Job> getAllJobs(long postedBy) throws Exception;

    Set<Job> getAllAvailableJobs(long sitterId) throws Exception;

}
