package com.care.service;

import com.care.dto.form.JobForm;

public interface SeekerService extends MemberService {
    //verify that the logged in user is a Seeker
    int postJob(JobForm jobForm);
    // verify memberId corresponds to the logged in user.
    int listJobs(int userId);
    // verify that this is invoked on the logged in user.
    int listApplications(int userId);

    int editJob(int userId, JobForm jobForm);
    int closeJob(int jobId);
}
