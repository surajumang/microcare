package com.care.service;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;

import java.util.List;

public interface SitterService extends Service{
    //make sure that the user invoking this is a Sitter.
    List<Job> listAllAvailableJobs(Member member);

    // check if logged in user is the one whose getApplications will be shown.
    List<Application> listAllApplications(Member member);

    //check if already applied to job
    int applyToJob(Member member, int jobId);

    // check if user is deleting an application which belongs to it.
    int deleteApplication(Member member, int applicationId);

}
