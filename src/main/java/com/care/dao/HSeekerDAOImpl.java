package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Seeker;

import java.sql.SQLException;
import java.util.List;

public class HSeekerDAOImpl implements SeekerDAO {
    @Override
    public int addSeeker(Seeker seeker) throws SQLException {
        return 0;
    }

    @Override
    public Seeker getSeeker(long seekerId) throws SQLException {
        return null;
    }

    @Override
    public List<Seeker> getSeekerByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public int editSeeker(long seekerId, Seeker seeker) throws SQLException {
        return 0;
    }

    @Override
    public List<Job> listAllMyJobs(long memberId) throws SQLException {
        return null;
    }

    @Override
    public List<Application> listAllApplicationsOnMyJob(long jobId) throws SQLException {
        return null;
    }
}
