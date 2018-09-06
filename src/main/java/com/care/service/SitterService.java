package com.care.service;

import com.care.dto.form.ApplicationDTO;
import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Sitter;

import java.sql.SQLException;
import java.util.List;

public interface SitterService extends Service{
    //make sure that the user invoking this is a Sitter.
    List<Job> listAllAvailableJobs(Member sitter);

    // check if logged in user is the one whose getApplications will be shown.
    List<Application> listAllApplications(Member sitter);

    //check if already applied to job
    Job getJob(int jobId);

    Sitter getSitter(int sitterId);

    int applyToJob(ApplicationDTO application);

    // check if user is deleting an application which belongs to it.
    int deleteApplication(Member sitter, int applicationId);

}
