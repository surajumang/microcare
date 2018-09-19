package com.care.dao;

import com.care.model.Application;
import com.care.model.Status;

import java.util.List;
import java.util.Set;

public interface ApplicationDAO extends DAO {

    int addApplication(Application application) throws Exception;

    Application getApplication(long applicationId) throws Exception;

    List<Application> getAllApplications(long sitterId) throws Exception;

    Set<Application> getAllApplicationsOnJob(long jobId) throws Exception;

    int setApplicationStatus(long applicationId, Status status) throws Exception;

    int setAllApplicationsStatusBySitter(long sitterId, Status status) throws Exception;

    int setAllApplicationStatusByJob(long jobId, Status status) throws Exception;

    int setAllApplicationsOnJobsPostedBy(long postedBy, Status status) throws Exception;
}
