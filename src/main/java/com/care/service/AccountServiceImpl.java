package com.care.service;

import com.care.dao.*;
import com.care.dto.form.EditForm;
import com.care.model.*;
import com.care.dto.form.RegistrationFormDTO;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {

    private Logger logger = Logger.getLogger("AccountService");
    public AccountServiceImpl() {  }

    public OperationStatus enroll(RegistrationFormDTO registrationFormDTO) {

        OperationStatus status = OperationStatus.FAILURE;
        int val = -1;
        logger.info("Called enroll");

        if (registrationFormDTO.getMemberType().equals("SEEKER") ){
            Seeker seeker = new Seeker();
            registrationFormDTO.setPassword(Hash.createHash(registrationFormDTO.getPassword()));

            ObjectMapper.mapObject(registrationFormDTO, seeker, true);
            val = addSeeker(seeker);
        }
        else {
            Sitter sitter = new Sitter();
            registrationFormDTO.setPassword(Hash.createHash(registrationFormDTO.getPassword()));
            ObjectMapper.mapObject(registrationFormDTO, sitter, true);
            val = addSitter(sitter);
        }
        if (val == 1){
            status = OperationStatus.SUCCESS;
        }
        return status;
    }

    private int addSeeker(Seeker seeker){

        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        logger.info(seeker.toString());
        int status = -1;
        try {
            status = seekerDAO.addSeeker(seeker);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't add", e);
        }
        return status;
    }

    private int addSitter(Sitter sitter){

        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        logger.info(sitter.toString());
        int status = -1;

        try {
            status = sitterDAO.addSitter(sitter);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't add", e);
        }
        return status;
    }

    public Member getMember(String memberId) {

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = null;
        try {
            member = memberDAO.getMember(memberId);
            logger.info(member.toString());
        } catch (java.sql.SQLException e) {
           logger.log(Level.SEVERE, "Error fetching member");
           member = Member.EMPTY_MEMBER;
        }
        return member;
    }
    /*
    Write code related to authorization here.
    Deleting member requires all jobs and application deletion as well based on the member type..

     */
    public OperationStatus deleteMember(Member member) {

        OperationStatus status = OperationStatus.SUCCESS;
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);

        try {
            if (member.getMemberType() == MemberType.SEEKER){
                logger.info("A SEEKer was deleted");
                applicationDAO.setAllApplicationsOnJobsPostedBy(member.getId(), Status.EXPIRED);
                jobDAO.setAllJobsStatus(member.getId(), Status.EXPIRED);
            }else {
                logger.info("A sitter was deleted");
                applicationDAO.setAllApplicationsStatusBySitter(member.getId(),Status.EXPIRED );
            }
            memberDAO.setMemberStatus(member.getId(), Status.CLOSED);

        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "Error fetching member");
            status = OperationStatus.FAILURE;
        }
        return status;
    }

    public int editMember(int memberId, EditForm editForm) {
        int status = -1;
        logger.info("Called enroll");

        if (editForm.getMemberType().equals("SEEKER") ){
            Seeker seeker = new Seeker();
            ObjectMapper.mapObject(editForm, seeker, true);
            status = editSeeker( memberId, seeker);
        }
        else {
            Sitter sitter = new Sitter();
            ObjectMapper.mapObject(editForm, sitter, true);
            status = editSitter(memberId, sitter);
        }
        return status ;
    }

    private int editSeeker(int seekerId, Seeker seeker) {

        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        logger.info(seeker.toString());
        int status = -1;
        try {
            status = seekerDAO.editSeeker(seekerId, seeker);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't add", e);
        }
        return status;
    }

    private int editSitter(int sitterId, Sitter sitter){
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        logger.info(sitter.toString());
        int status = -1;
        try {
            status = sitterDAO.editSitter(sitterId, sitter);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't add", e);
        }
        return status;
    }

}
