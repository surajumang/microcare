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

    public boolean registerMember(RegistrationFormDTO registrationFormDTO) {

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

    public int deleteMember(String email) {

        return 0;
    }

    public int editMember(String email, RegistrationFormDTO registrationFormDTO) {
        return 0;
    }

    public int editMember(String email, Member member) {
        return 0;
    }
}
