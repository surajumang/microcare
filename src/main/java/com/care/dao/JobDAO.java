package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.model.Job;
import com.care.model.Status;

import java.util.List;

public interface JobDAO extends DAO {
    int addJob(Job job) ;

    int expireStaleJobs() ;

    int editJob(Job job) ;

    Job getJob(long jobId) ;

    List<Job> getAllJobs(long postedBy);

    List<Job> getAllAvailableJobs(long sitterId) ;

}
