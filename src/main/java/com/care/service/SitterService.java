package com.care.service;

import com.care.dto.form.ApplicationDTO;
import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;
import com.care.model.Sitter;

import java.util.List;

public interface SitterService extends Service{

    List<Job> listAllAvailableJobs(Member sitter, OperationStatus operationStatus);

    List<Application> listAllApplications(Member sitter, OperationStatus operationStatus);

    Job getJob(int jobId, OperationStatus operationStatus);

    Sitter getSitter(int sitterId, OperationStatus operationStatus);

    OperationStatus applyToJob(ApplicationDTO application);

    OperationStatus deleteApplication(Member sitter, int applicationId);

}
