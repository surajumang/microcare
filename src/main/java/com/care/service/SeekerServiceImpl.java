package com.care.service;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dao.ConnectionUtil;
import com.care.dao.MemberDAOImpl;
import com.care.dto.form.LoginDetails;
import com.care.dto.form.RegistrationForm;

import javax.servlet.Servlet;

public class SeekerServiceImpl implements SeekerService {

    private static SeekerServiceImpl ourInstance = new SeekerServiceImpl();
    private MemberService memberService = ServiceFactory.get(MemberServiceImpl.class);

    private SeekerServiceImpl(){

    }

    public boolean registerMember(RegistrationForm registrationForm){
        //boolean b = memberService.registerMember()
//        System.err.println(b + " Db query status");
        return false;
    }

}

//
//    Member member = new Member();
//        member.setFirstName(registrationForm.getFirstName());
//                member.setLastName(registrationForm.getLastName());
//                member.setEmail(registrationForm.getEmail());
//                member.setAddress(registrationForm.getAddress());
//                member.setPhone(Integer.parseInt(registrationForm.getPhone()));
//                member.setPassword(registrationForm.getPassword());
//                member.setZipCode(Integer.parseInt(registrationForm.getZipCode()));
//                member.setMemberType(MemberType.SEEKER);
