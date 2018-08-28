package com.care.dao;

import com.care.beans.Member;
import com.care.service.MemberService;
import com.care.service.MemberServiceImpl;
import com.care.service.ServiceFactory;

public class SeekerDAOImpl implements SeekerDAO {

    private MemberService memberService = ServiceFactory.get(MemberServiceImpl.class);

    private static SeekerDAOImpl ourInstance = new SeekerDAOImpl();
    public static SeekerDAOImpl getInstance(){
        return ourInstance;
    }

    private SeekerDAOImpl(){

    }

    public int postJob() {
        return 0;
    }

    public int editJob() {
        return 0;
    }

    public int deleteJob() {
        return 0;
    }
    // Methods from MemberDAO.
    public int addMember(Member member) {
        return 0;
    }

    public int editMember(int memberId) {
        return 0;
    }

    public int deleteMember(int memberId) {
        return 0;
    }
}
