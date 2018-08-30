package com.care.service;

import com.care.dto.form.ApplicationFormDTO;
import com.care.dto.form.JobFormDTO;

import java.util.List;

public interface SitterService extends Service{
    //make sure that the user invoking this is a Sitter.
    List<JobFormDTO> listAllJobs();
    // check if logged in user is the one whose applications will be shown.
    List<ApplicationFormDTO> listAllApplications(int memberId);
    // check if logged in user is the one whose applications will be shown.
    List<ApplicationFormDTO> listApplication(int applicationId);
    //check if already applied to job
    int applyToJob(int jobId);
    // check if user is deleting an application which belongs to it.
    int deleteApplication(int applicationId);

}
