package com.care.dao;

import java.sql.SQLException;

public interface SitterDAO extends DAO {
    // jobId needs to be existing
    int applyToJob(int memberId, int jobId) throws SQLException;
    int getJobs() throws SQLException;
    int getJobs(int zipCode) throws SQLException;
    int closeApplication(int applicationId) throws SQLException;

}
