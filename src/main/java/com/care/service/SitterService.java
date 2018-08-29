package com.care.service;

public interface SitterService extends MemberService{
    int listAllJobs();
    // check if logged in user is the one whose applications will be shown.
    int listAllApplications(int memberId);
    int listApplication(int applicationId);
    //check if already applied to job
    int applyToJob(int jobId);
}
