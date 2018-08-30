package com.care.service;

import com.care.dto.form.ApplicationFormDTO;
import com.care.dto.form.JobFormDTO;

import java.util.List;

public interface SeekerService extends Service {

    /*
    Since verification is required for every method in this class, the implementing class will use
    a utility method to enforce this check before performing the actual operation.
    */

    /*verify that the logged in user is a Seeker
     every action is to be performed on the currently logged in user.
    */
    int postJob(JobFormDTO jobForm);
    // verify memberId corresponds to the logged in user.
    List<JobFormDTO> listJobs();
    // verify that this is invoked on the logged in user.
    // check that this job belongs to the logged in user.
    List<ApplicationFormDTO> listApplicationsOnJob(int jobId);

    int editJob(int userId, JobFormDTO jobForm);

    int closeJob(int jobId);
}
