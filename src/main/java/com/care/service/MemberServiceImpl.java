package com.care.service;

import com.care.beans.Member;
import com.care.dao.DAOFactory;
import com.care.dao.MemberDAO;
import com.care.dao.MemberDAOImpl;
import com.care.dto.form.RegistrationFormDTO;

public class MemberServiceImpl implements MemberService {
    private static MemberServiceImpl ourInstance = new MemberServiceImpl();

    public static MemberServiceImpl getInstance() {
        return ourInstance;
    }

//    public <T extends Service> T create() {
//        return (T)getInstance();
//    }

    private MemberServiceImpl() {
    }

    public boolean registerMember(RegistrationFormDTO registrationFormDTO) {

        return false;
    }

    public Member getMember(String memberId) {

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = null;
        try {
            member = memberDAO.getMember(memberId);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
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
