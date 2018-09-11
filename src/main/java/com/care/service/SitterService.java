package com.care.service;

import com.care.form.ApplicationForm;
import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Sitter;

import java.util.List;

public interface SitterService extends Service{

    List<Job> listAllAvailableJobs(Member sitter);

    List<Application> listAllApplications(Member sitter);

    Job getJob(long jobId);

    Sitter getSitter(long sitterId);

    List<Sitter> getSitterByEmail(String email);

    OperationStatus applyToJob(ApplicationForm application);

    OperationStatus deleteApplication(Member sitter, long applicationId);

}
