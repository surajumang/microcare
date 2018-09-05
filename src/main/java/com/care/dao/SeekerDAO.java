package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Seeker;

import java.sql.SQLException;
import java.util.List;

public interface SeekerDAO extends DAO {

    int addSeeker(Seeker seeker) throws SQLException;

    int postJob(Job job) throws SQLException;

    int editJob(Job job) throws SQLException;

    int deleteJob(int jobId) throws SQLException;

    List<Job> listAllMyJobs(int memberId) throws SQLException;

    List<Application> listAllApplicationsOnMyJob(int jobId) throws SQLException;

    int closeJob(int jobId) throws SQLException;
}
