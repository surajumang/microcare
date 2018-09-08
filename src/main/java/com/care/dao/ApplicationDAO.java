package com.care.dao;

import com.care.model.Application;
import com.care.model.Status;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDAO extends DAO {

    int addApplication(Application application) throws SQLException;

    Application getApplication(long applicationId) throws SQLException;

    List<Application> getAllApplications(long sitterId) throws SQLException;

    List<Application> getAllApplicationsOnJob(long jobId) throws SQLException;

    int setApplicationStatus(long applicationId, Status status) throws SQLException;

    int setAllApplicationsStatusBySitter(long sitterId, Status status) throws SQLException;

    int setAllApplicationStatusByJob(long jobId, Status status)throws SQLException;

    int setAllApplicationsOnJobsPostedBy(long postedBy, Status status) throws SQLException;
}
