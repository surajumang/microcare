package com.care.dao;

import com.care.model.Application;
import com.care.model.Status;

import java.sql.SQLException;
import java.util.List;

public class HApplicationDAOImpl implements ApplicationDAO {
    @Override
    public int addApplication(Application application) throws SQLException {
        return 0;
    }

    @Override
    public Application getApplication(long applicationId) throws SQLException {
        return null;
    }

    @Override
    public List<Application> getAllApplications(long sitterId) throws SQLException {
        return null;
    }

    @Override
    public List<Application> getAllApplicationsOnJob(long jobId) throws SQLException {
        return null;
    }

    @Override
    public int setApplicationStatus(long applicationId, Status status) throws SQLException {
        return 0;
    }

    @Override
    public int setAllApplicationsStatusBySitter(long sitterId, Status status) throws SQLException {
        return 0;
    }

    @Override
    public int setAllApplicationStatusByJob(long jobId, Status status) throws SQLException {
        return 0;
    }

    @Override
    public int setAllApplicationsOnJobsPostedBy(long postedBy, Status status) throws SQLException {
        return 0;
    }
}
