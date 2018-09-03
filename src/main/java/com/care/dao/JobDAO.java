package com.care.dao;

import com.care.model.Job;
import com.care.model.Member;

import java.sql.SQLException;
import java.util.List;

public interface JobDAO extends DAO {
    int addJob(Job job) throws SQLException;
    // only owners will be able to edit or delete jobs.
    boolean checkOwner(int postedBy, int jobId) throws SQLException;
    // deletion is nothing but marking the status flag as INACTIVE
    int deleteJob(Member member, int jobId) throws SQLException;
    int deleteAllJobs(Member member) throws SQLException;
    int editJob(int jobId) throws SQLException;
    Job getJob(Member member, int jobId) throws SQLException;
    List<Job> getAllJobs(int postedBy) throws SQLException;
    List<Job> getAllJobs()throws SQLException;

}
