package com.care.service;

import com.care.beans.Member;
import com.care.dao.DAOFactory;
import com.care.dao.MemberDAO;
import com.care.dao.MemberDAOImpl;
import com.care.dto.form.LoginDetails;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger logger = Logger.getLogger("AuthenticationServiceImpl");

    private static AuthenticationServiceImpl ourInstance = new AuthenticationServiceImpl();

    public static AuthenticationServiceImpl getInstance(){
        logger.info(" returns --> " + ourInstance);
        return ourInstance;
    }
    private AuthenticationServiceImpl(){

    }

    public boolean loginUser(LoginDetails loginDetails) {
        logger.info("User trying to Log in");

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = null;
        try {
            member = memberDAO.getMember(loginDetails.getEmail());
        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "Can't fetch member for Login");
        }
        boolean isLoginSuccessful = false;

        if(member != null){
            logger.info("Member Exists ");
            logger.info(loginDetails.getPassword());
            logger.info(member.getPassword());
            if (loginDetails.getPassword().equals(member.getPassword())){
                isLoginSuccessful = true;
                logger.info("LOgin success");
            }
        }
        return isLoginSuccessful;
    }

    public boolean logout() {
        //CommonUtil.removeLoggedInUser();
        return false;
    }

    public int updatePassword() {
        return 0;
    }

    public int forgotPassword() {
        return 0;
    }

    public boolean isLoggedIn(int userId) {
        //return userId == CommonUtil.getLoggedInUserFromSession().getId();
        return false;
    }
}
