package com.care.service;

import com.care.beans.Job;
import com.care.beans.Member;
import com.care.dao.DAOFactory;
import com.care.dao.JobDAO;
import com.care.dao.JobDAOImpl;
import com.care.dto.form.ApplicationFormDTO;
import com.care.dto.form.JobFormDTO;

import java.util.ArrayList;
import java.util.List;

public class SeekerServiceImpl implements SeekerService {

    private static SeekerServiceImpl ourInstance = new SeekerServiceImpl();
    public static SeekerServiceImpl getInstance(){
        return ourInstance;
    }

    private SeekerServiceImpl(){

    }

    public int postJob(JobFormDTO jobForm) {
        return 0;
    }

    public List<JobFormDTO> listJobs() {
        List<JobFormDTO> memberJobDTO = new ArrayList<JobFormDTO>();

        if(AuthenticationUtil.isMemberLoggedIn()){
            Member member = AuthenticationUtil.getLoggedInUser();
            JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
            List<Job> memberJobs = jobDAO.getAllJobs(member.getId());

            for(Job job: memberJobs){
                JobFormDTO jobFormDTO = null;
                ObjectMapper.mapObject(job, jobFormDTO, true);
                memberJobDTO.add(jobFormDTO);
            }
        }
        return memberJobDTO;
    }

    public List<ApplicationFormDTO> listApplicationsOnJob(int jobId) {
        return null;
    }

    public int editJob(int userId, JobFormDTO jobForm) {
        return 0;
    }

    public int closeJob(int jobId) {
        return 0;
    }
}

//
//    Member member = new Member();
//        member.setFirstName(registrationForm.getFirstName());
//                member.setLastName(registrationForm.getLastName());
//                member.setEmail(registrationForm.getEmail());
//                member.setAddress(registrationForm.getAddress());
//                member.setPhone(Integer.parseInt(registrationForm.getPhone()));
//                member.setPassword(registrationForm.getPassword());
//                member.setZipCode(Integer.parseInt(registrationForm.getZipCode()));
//                member.setMemberType(MemberType.SEEKER);
