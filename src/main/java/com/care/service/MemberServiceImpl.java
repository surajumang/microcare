package com.care.service;

import com.care.beans.Member;
import com.care.dto.form.RegistrationForm;

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

    public boolean registerMember(RegistrationForm registrationForm) {

        return false;
    }

    public int getMember(int memberId) {
        return 0;
    }

    public int deleteMember(int memberId) {

        return 0;
    }

    public int editMember(int memberId, RegistrationForm registrationForm) {
        return 0;
    }

    public int editMember(int memberId, Member member) {
        return 0;
    }
}
