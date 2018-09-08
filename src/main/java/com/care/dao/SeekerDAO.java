package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Seeker;

import java.sql.SQLException;
import java.util.List;

public interface SeekerDAO extends DAO {

    int addSeeker(Seeker seeker) throws SQLException;

    Seeker getSeeker(long seekerId) throws SQLException;

    List<Seeker> getSeekerByEmail(String email) throws SQLException;

    int editSeeker(long seekerId, Seeker seeker) throws SQLException;

    List<Job> listAllMyJobs(long memberId) throws SQLException;

    List<Application> listAllApplicationsOnMyJob(long jobId) throws SQLException;

}
