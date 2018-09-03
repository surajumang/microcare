package com.care.service;

import com.care.model.Job;
import com.care.model.Member;
import com.care.dto.form.ApplicationDTO;
import com.care.dto.form.JobDTO;

import java.util.List;

public interface SeekerService extends Service {

    boolean getSeeker();

    int postJob(Member member, JobDTO jobForm);

    List<Job> listJobs(Member member) ;

    List<ApplicationDTO> listApplicationsOnJob(Member member, int jobId) ;

    int editJob(Member member, JobDTO jobForm) ;

    int closeJob(Member member, int jobId) ;
}
