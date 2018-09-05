package com.care.dao;

import com.care.model.Application;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDAO extends DAO {

    int addApplication(Application application) throws SQLException;

    Application getApplication(int applicationId) throws SQLException;

    List<Application> getAllApplications(int sitterId) throws SQLException;

    List<Application> getAllApplicationsOnJob(int jobId) throws SQLException;

    int deleteApplication(int applicationId) throws SQLException;

    int deleteAllApplications(int sitterId) throws SQLException;
}
