package com.care.dao;

import com.care.model.Job;
import com.care.model.Status;

import java.sql.SQLException;
import java.util.List;

public class HJobDAOImpl implements JobDAO {
    @Override
    public int addJob(Job job) throws SQLException {
        return 0;
    }

    @Override
    public int setJobStatus(long jobId, Status status) throws SQLException {
        return 0;
    }

    @Override
    public int setAllJobsStatus(long postedBy, Status status) throws SQLException {
        return 0;
    }

    @Override
    public int expireStaleJobs() throws SQLException {
        return 0;
    }

    @Override
    public int editJob(Job jobId) throws SQLException {
        return 0;
    }

    @Override
    public Job getJob(long jobId) throws SQLException {
        return null;
    }

    @Override
    public List<Job> getAllJobs(long postedBy) throws SQLException {
        return null;
    }

    @Override
    public List<Job> getAllAvailableJobs(long sitterId) throws SQLException {
        return null;
    }
}
