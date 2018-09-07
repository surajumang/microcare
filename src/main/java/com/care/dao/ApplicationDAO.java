package com.care.dao;

import com.care.model.Application;
import com.care.model.Status;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDAO extends DAO {

    int addApplication(Application application) throws SQLException;

    Application getApplication(int applicationId) throws SQLException;

    List<Application> getAllApplications(int sitterId) throws SQLException;

    List<Application> getAllApplicationsOnJob(int jobId) throws SQLException;

    int setApplicationStatus(int applicationId, Status status) throws SQLException;

    int setAllApplicationsStatusBySitter(int sitterId, Status status) throws SQLException;

    int setAllApplicationStatusByJob(int jobId, Status status)throws SQLException;

    int setAllApplicationsOnJobsPostedBy(int postedBy, Status status) throws SQLException;
}
