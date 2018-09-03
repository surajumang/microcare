package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;

import java.sql.SQLException;
import java.util.List;

public interface SeekerDAO {
    boolean postJob(Job job) throws SQLException;
    int editJob(int jobId, Job job) throws SQLException;
    boolean deleteJob(int jobId) throws SQLException;
    List<Job> listAllMyJobs(int memberId) throws SQLException;
    List<Application> listAllApplicationsOnMyJob(int jobId) throws SQLException;
    boolean closeJob(int jobId) throws SQLException;
}
