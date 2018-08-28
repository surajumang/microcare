package com.care.dao;

import com.care.beans.Job;

import java.util.List;

public interface JobDAO extends DAO {
    int addJob(Job job);
    // only owners will be able to edit or delete jobs.
    boolean checkOwner(int postedBy, int jobId);
    // deletion is nothing but marking the status flag as INACTIVE
    int deleteJob(int jobId);
    int deleteAllJobs(int postedBy);
    int editJob(int jobId);
    Job getJob(int jobId);
    List<Job> getAllJobs(int postedBy);

}
