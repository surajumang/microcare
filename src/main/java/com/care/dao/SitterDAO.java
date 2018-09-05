package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;

import java.sql.SQLException;
import java.util.List;

public interface SitterDAO extends DAO {
    // jobId needs to be existing
    int applyToJob(Application application) throws SQLException;

    List<Job> getJobs() throws SQLException;

    int closeApplication(int applicationId) throws SQLException;

}
