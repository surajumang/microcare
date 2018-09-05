package com.care.service;

import com.care.model.Member;
import com.care.dao.DAOFactory;
import com.care.dao.MemberDAO;
import com.care.dao.MemberDAOImpl;
import com.care.dto.form.RegistrationFormDTO;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {

    private Logger logger = Logger.getLogger("AccountService");
    public AccountServiceImpl() {  }

    public boolean enroll(RegistrationFormDTO registrationFormDTO) {
        /*
        Testing ObjectMapper. Check whether a seeker or a sitter has to be added.
         */
        logger.info("Called enroll");
        if (registrationFormDTO.getMemberType().equals("SEEKER") ){
            Seeker seeker = new Seeker();
            logger.info(registrationFormDTO.toString());
            ObjectMapper.mapObject(registrationFormDTO, seeker, true);
            logger.info(seeker.toString());
        }else{
            Sitter sitter = new Sitter();
            logger.info(registrationFormDTO.toString());
            ObjectMapper.mapObject(registrationFormDTO, sitter, true);
            logger.info(sitter.toString());
        }


        return false;
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

    public int editMember(int memberId, RegistrationFormDTO registrationFormDTO) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);

        try {
            // Object Mapper needed here.

            memberDAO.editMember(new Member());
            logger.info(memberId + " ");
        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "Error fetching member");
        }
        return 0;
    }

    public int editMember(String email, Member member) {
        return 0;
    }
}
