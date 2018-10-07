package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.model.Job;
import com.care.model.Status;

import java.util.List;

public interface JobDAO extends DAO {
    int addJob(Job job) throws Exception;

    int expireStaleJobs() throws Exception;

    int editJob(Job job) throws DataWriteException;

    Job getJob(long jobId) throws DataReadException;

    List<Job> getAllJobs(long postedBy) throws Exception;

    List<Job> getAllAvailableJobs(long sitterId) throws Exception;

}
