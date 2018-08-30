package com.care.service;

import com.care.dto.form.ApplicationDTO;
import com.care.dto.form.JobDTO;

import java.util.List;

public class SitterServiceImpl implements SitterService {
    private static SitterServiceImpl ourInstance = new SitterServiceImpl();

    public static SitterServiceImpl getInstance(){
        return ourInstance;
    }

    private SitterServiceImpl(){

    }

    public List<JobDTO> listAllJobs() {
        return null;
    }

    public List<ApplicationDTO> listAllApplications(int memberId) {
        return null;
    }

    public List<ApplicationDTO> listApplication(int applicationId) {
        return null;
    }

    public int applyToJob(int jobId) {
        return 0;
    }

    public int deleteApplication(int applicationId) {
        return 0;
    }
}
