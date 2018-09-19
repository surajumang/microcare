package com.care.dao;

import com.care.model.Job;
import com.care.model.Status;

import java.util.List;

public interface JobDAO extends DAO {
    int addJob(Job job) throws Exception;

    int setJobStatus(long jobId, Status status) throws Exception;

    int setAllJobsStatus(long postedBy, Status status) throws Exception;

    int expireStaleJobs() throws Exception;

    int editJob(Job job) throws Exception;

    Job getJob(long jobId) throws Exception;

    List<Job> getAllJobs(long postedBy) throws Exception;

    List<Job> getAllAvailableJobs(long sitterId) throws Exception;

}
