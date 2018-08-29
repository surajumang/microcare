package com.care.service;

public interface SitterService extends MemberService{
    //make sure that the user invoking this is a Sitter.
    int listAllJobs();
    // check if logged in user is the one whose applications will be shown.
    int listAllApplications(int memberId);
    // check if logged in user is the one whose applications will be shown.
    int listApplication(int applicationId);
    //check if already applied to job
    int applyToJob(int jobId);
    // check if user is deleting an application which belongs to it.
    int deleteApplication(int applicationId);
    // make sure that the logged in user is performing this operation.
    int closeAccount(int sitterId);
}
