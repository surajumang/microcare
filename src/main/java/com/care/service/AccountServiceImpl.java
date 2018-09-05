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

    /*
    Map the registration form to Member either Seeker/Sitter and then put it in both Seeker/Sitter, Member
     */
    public boolean enroll(RegistrationFormDTO registrationFormDTO) {
        Member member = new Member();
        //registrationFormDTO.get
        ObjectMapper.mapObject(registrationFormDTO, member, false);
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
