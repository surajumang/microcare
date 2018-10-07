package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.model.Application;
import com.care.model.Status;

import java.util.List;
import java.util.Set;

public interface ApplicationDAO extends DAO {

    int addApplication(Application application) ;

    Application getApplication(long applicationId) ;

    List<Application> getAllApplications(long sitterId) ;

    Set<Application> getAllApplicationsOnJob(long jobId);
}
