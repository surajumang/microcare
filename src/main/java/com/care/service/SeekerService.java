package com.care.service;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;
import com.care.dto.form.JobDTO;
import com.care.model.Seeker;

import java.util.List;

public interface SeekerService extends Service {

    Seeker getSeeker(int seekerId);

    Job getJob(int jobId);

    int postJob(Member member, JobDTO jobForm);

    List<Job> listJobs(Member member) ;

    List<Application> getApplications(Member member, int jobId) ;

    int editJob(Member member, JobDTO jobForm) ;

    int closeJob(Member member, int jobId) ;
}
