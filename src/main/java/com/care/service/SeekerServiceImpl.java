package com.care.service;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dao.ConnectionUtil;
import com.care.dao.MemberDAO;
import com.care.form.RegistrationForm;

public class SeekerServiceImpl implements SeekerService {

    public static void registerMember(RegistrationForm registrationForm){

        Member member = new Member();
        member.setFirstName(registrationForm.getFirstname());
        member.setLastName(registrationForm.getLastname());
        member.setEmail(registrationForm.getEmail());
        member.setAddress(registrationForm.getAddress());
        member.setPhone(Integer.parseInt(registrationForm.getPhone()));
        member.setPassword(registrationForm.getPassword());
        member.setZipCode(Integer.parseInt(registrationForm.getZipcode()));
        member.setMemberType(MemberType.SEEKER);


        boolean b = MemberDAO.addMember(member, ConnectionUtil.getConnection());
        System.err.println("b" + "Db query status");
    }
}
