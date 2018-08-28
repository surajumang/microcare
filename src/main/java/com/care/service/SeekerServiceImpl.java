package com.care.service;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dao.ConnectionUtil;
import com.care.dao.MemberDAOImpl;
import com.care.dto.form.LoginDetails;
import com.care.dto.form.RegistrationForm;

public class SeekerServiceImpl implements SeekerService {

    private static SeekerServiceImpl ourInstance = new SeekerServiceImpl();
    private MemberService memberService = null;

    private SeekerServiceImpl(){

    }

    public boolean registerMember(RegistrationForm registrationForm){

        Member member = new Member();
        member.setFirstName(registrationForm.getFirstName());
        member.setLastName(registrationForm.getLastName());
        member.setEmail(registrationForm.getEmail());
        member.setAddress(registrationForm.getAddress());
        member.setPhone(Integer.parseInt(registrationForm.getPhone()));
        member.setPassword(registrationForm.getPassword());
        member.setZipCode(Integer.parseInt(registrationForm.getZipCode()));
        member.setMemberType(MemberType.SEEKER);


        boolean b = MemberDAOImpl.addMember(member, ConnectionUtil.getConnection());
        System.err.println(b + " Db query status");
        return b;
    }
    public Member login(LoginDetails loginDetails){
        return MemberDAOImpl.checkMember(loginDetails, ConnectionUtil.getConnection());

    }
}
