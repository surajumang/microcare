package com.care.service;

import com.care.beans.Job;
import com.care.beans.Member;
import com.care.dao.*;
import com.care.dto.form.ApplicationDTO;
import com.care.dto.form.JobDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeekerServiceImpl implements SeekerService {

    private static SeekerServiceImpl ourInstance = new SeekerServiceImpl();
    public static SeekerServiceImpl getInstance(){
        return ourInstance;
    }

    private SeekerServiceImpl(){

    }

    public int postJob(JobDTO jobForm) throws SQLException {
        return 0;
    }

    public List<JobDTO> listJobs(int userId) throws SQLException {
        return null;
    }

    /*
    List all jobs posted by the currently logged in seeker.
    Fields to be listed are [Title, Status, StartDate, EndDate]
    along with operations allowed to be performed on them.[Edit, List Applications, CloseJob(confiramtion)]
     */
    public List<JobDTO> listJobs(Member member) {
        List<JobDTO> memberJobDTO = new ArrayList<JobDTO>();

        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        List<Job> memberJobs = null;
        try {
            memberJobs = jobDAO.getAllJobs(member.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Job job: memberJobs){
            JobDTO jobDTO = null;
            ObjectMapper.mapObject(job, jobDTO, true);
            memberJobDTO.add(jobDTO);
        }
        return memberJobDTO;
    }
    /*
    List all the applications on a Job selected by the current seeker.
    Make sure that the job on which the operation is to be performed belongs to the currently logged in user.
    Fields to be displayed are [Job title, SitterName, ApplicationStatus, ExpectedPay]
     */
    public List<ApplicationDTO> listApplicationsOnJob(int jobId) throws SQLException {
        List<ApplicationDTO> applicationDTOList = new ArrayList<ApplicationDTO>();

        if(! verifyJobBelongsToMember(jobId)){
            // Throwing exception is also a possibilty.
            return null;
        }
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);


        return null;
    }
    /*

     */
    private boolean verifyJobBelongsToMember(int jobId){

        return false;
    }

    public int editJob(int userId, JobDTO jobForm) throws SQLException {
        return 0;
    }

    public int closeJob(int jobId) throws SQLException {
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
