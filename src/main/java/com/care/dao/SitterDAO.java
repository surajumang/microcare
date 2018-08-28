package com.care.dao;

public interface SitterDAO extends MemberDAO {
    // jobId needs to be existing
    int applyToJob(int memberId, int jobId);
    int getJobs();
    int getJobs(int zipCode);
    int closeApplication(int applicationId);

}
