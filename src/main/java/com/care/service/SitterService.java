package com.care.service;

import com.care.beans.Job;
import com.care.dto.form.ApplicationDTO;

import java.util.List;

public interface SitterService extends Service{
    //make sure that the user invoking this is a Sitter.
    List<Job> listAllJobs();
    // check if logged in user is the one whose applications will be shown.
    List<ApplicationDTO> listAllApplications(int memberId);
    // check if logged in user is the one whose applications will be shown.
    List<ApplicationDTO> listApplication(int applicationId);
    //check if already applied to job
    int applyToJob(int jobId);
    // check if user is deleting an application which belongs to it.
    int deleteApplication(int applicationId);

}
