package com.care.service;

import com.care.dao.*;
import com.care.dto.form.EditForm;
import com.care.model.Member;
import com.care.model.Seeker;
import com.care.model.Sitter;
import com.care.dto.form.RegistrationFormDTO;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {

    private Logger logger = Logger.getLogger("AccountService");
    public AccountServiceImpl() {  }

    public boolean enroll(RegistrationFormDTO registrationFormDTO) {
        /*
        Testing ObjectMapper. Check whether a seeker or a sitter has to be added.
         */
        int status = -1;
        //MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        logger.info("Called enroll");

        if (registrationFormDTO.getMemberType().equals("SEEKER") ){
            Seeker seeker = new Seeker();
            ObjectMapper.mapObject(registrationFormDTO, seeker, true);
            status = addSeeker(seeker);
        }
        else {
            Sitter sitter = new Sitter();
            ObjectMapper.mapObject(registrationFormDTO, sitter, true);
            status = addSitter(sitter);
        }
        return status == 1 ;
    }

    private int addSeeker(Seeker seeker){
        //MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        logger.info(seeker.toString());
        int status = -1;
        try {
            //memberDAO.addMember(seeker);
            //seeker.setId(memberDAO.getMember(seeker.getEmail()).getId());
            status = seekerDAO.addSeeker(seeker);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't add", e);
        }
        return status;
    }

    private int addSitter(Sitter sitter){
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        logger.info(sitter.toString());
        int status = -1;

        try {
//            memberDAO.addMember(sitter);
//            sitter.setId(memberDAO.getMember(sitter.getEmail()).getId());
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
    [TODO]
     */
    public int deleteMember(int memberId) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        try {
            memberDAO.deleteMember(memberId);
            logger.info(memberId + " ");
        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "Error fetching member");
        }
        return 0;
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
