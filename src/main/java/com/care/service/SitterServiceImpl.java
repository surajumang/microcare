package com.care.service;

import com.care.beans.Member;
import com.care.dto.form.ApplicationFormDTO;
import com.care.dto.form.JobFormDTO;
import com.care.dto.form.RegistrationFormDTO;

import java.util.List;

public class SitterServiceImpl implements SitterService {
    private static SitterServiceImpl ourInstance = new SitterServiceImpl();
    public static <T extends Service> T getInstance(){
        return (T)ourInstance;
    }

    private SitterServiceImpl(){

    }

    public List<JobFormDTO> listAllJobs() {
        return null;
    }

    public List<ApplicationFormDTO> listAllApplications(int memberId) {
        return null;
    }

    public List<ApplicationFormDTO> listApplication(int applicationId) {
        return null;
    }

    public int applyToJob(int jobId) {
        return 0;
    }

    public int deleteApplication(int applicationId) {
        return 0;
    }
}
