package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Seeker;

import java.util.Set;

public interface SeekerDAO extends DAO {

    int addSeeker(Seeker seeker) throws Exception;

    Seeker getSeeker(long seekerId) throws Exception;
    /*
    Used to implement the Search features
     */
    Set<Seeker> getSeekerByEmail(String email) throws Exception;

    int editSeeker(long seekerId, Seeker seeker) throws Exception;

    Set<Job> listAllMyJobs(long seekerId) throws Exception;

    Set<Application> listAllApplicationsOnMyJob(long jobId) throws Exception;

}
