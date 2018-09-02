package com.care.service;

import com.care.beans.Application;
import com.care.beans.Job;
import com.care.beans.Member;

import java.util.List;

public interface SitterService extends Service{
    //make sure that the user invoking this is a Sitter.
    List<Job> listAllJobs();
    // check if logged in user is the one whose applications will be shown.
    List<Application> listAllApplications(Member member, int memberId);
    // check if logged in user is the one whose applications will be shown.
    List<Application> listApplication(Member member, int applicationId);
    //check if already applied to job
    int applyToJob(Member member, int jobId);
    // check if user is deleting an application which belongs to it.
    int deleteApplication(Member member, int applicationId);

}
