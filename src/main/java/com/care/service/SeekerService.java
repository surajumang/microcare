package com.care.service;

import com.care.beans.Job;
import com.care.beans.Member;
import com.care.dto.form.ApplicationDTO;
import com.care.dto.form.JobDTO;

import java.util.List;

public interface SeekerService extends Service {

    /*
    Since verification is required for every method in this class, the implementing class will use
    a utility method to enforce this check before performing the actual operation.
    */

    /*verify that the logged in user is a Seeker
     every action is to be performed on the currently logged in user.
    */
    int postJob(Member member, JobDTO jobForm);
    // verify memberId corresponds to the logged in user.
    List<Job> listJobs(Member member) ;
    // verify that this is invoked on the logged in user.
    // check that this job belongs to the logged in user.
    List<ApplicationDTO> listApplicationsOnJob(Member member, int jobId) ;

    int editJob(Member member, JobDTO jobForm) ;

    int closeJob(Member member, int jobId) ;
}
