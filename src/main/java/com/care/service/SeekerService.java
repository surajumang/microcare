package com.care.service;

import com.care.exception.IllegalApplicationAccessException;
import com.care.exception.JobNotPostedByUserException;
import com.care.form.JobForm;
import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Seeker;

import java.util.List;

public interface SeekerService extends Service {

    Seeker getSeeker(long seekerId);

    List<Seeker> getSeekerByEmail(String email);

    Job getJob(Member member, long jobId) throws JobNotPostedByUserException;

    OperationStatus postJob(Member member, JobForm jobForm);

    List<Job> listJobs(Member member);

    List<Application> getApplications(Member member, long jobId) throws IllegalApplicationAccessException;

    OperationStatus editJob(Member member, JobForm jobForm);

    OperationStatus closeJob(Member member, long jobId) throws JobNotPostedByUserException;
}
