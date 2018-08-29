package com.care.service;

import com.care.dto.form.JobForm;

public interface SeekerService extends MemberService {
    int postJob(JobForm jobForm);
    int listJobs(int userId);
    int listApplications(int userId);
    int editJob(int userId, JobForm jobForm);
    int closeJob(int jobId);
}
