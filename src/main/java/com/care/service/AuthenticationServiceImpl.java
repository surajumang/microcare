package com.care.service;

import com.care.beans.Member;
import com.care.dao.DAOFactory;
import com.care.dao.MemberDAO;
import com.care.dao.MemberDAOImpl;
import com.care.dto.form.LoginDetails;

public class AuthenticationServiceImpl implements AuthenticationService {

    public boolean loginUser(LoginDetails loginDetails) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = memberDAO.getMember(loginDetails.getEmail());
        boolean isLoginSuccessful = false;

        if(member != null){
            if (loginDetails.getPassword().equals(member.getPassword())){
                isLoginSuccessful = true;
                AuthenticationUtil.setLoggedInUser(member);
            }
        }
        return isLoginSuccessful;
    }

    public boolean logout() {
        AuthenticationUtil.removeLoggedInUser();
        return false;
    }

    public int updatePassword() {
        return 0;
    }

    public int forgotPassword() {
        return 0;
    }

    public boolean isLoggedIn(int userId) {
        return userId == AuthenticationUtil.getLoggedInUser().getId();
    }
}
