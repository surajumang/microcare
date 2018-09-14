package com.care.service;

import com.care.form.LoginForm;
import com.care.form.PasswordForm;
import com.care.model.Member;
import com.care.dao.DAOFactory;
import com.care.dao.MemberDAO;
import com.care.dao.MemberDAOImpl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger logger = Logger.getLogger("AuthenticationServiceImpl");
    public AuthenticationServiceImpl(){

    }

    public OperationStatus loginUser(LoginForm loginForm) {
        logger.info("User trying to Log in");
        OperationStatus status = OperationStatus.FAILURE;

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = null;
        try {
            member = memberDAO.getMember(loginForm.getEmail());
            logger.info(member + " ");
        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "Can't fetch member for LoginAction", e);
            status = OperationStatus.FAILURE;
        }

        if(member != null){
            logger.info("Member Exists " + member);

            if (Hash.createHash(loginForm.getPassword()).equals(member.getPassword())){
                status =OperationStatus.SUCCESS;
                logger.info("LoginAction success");
            }
        }
        logger.info(status.name() + "STATUS");
        return status;
    }

    public boolean logout() {
        //CommonUtil.removeLoggedInUser();
        return false;
    }

    public OperationStatus updatePasswordWithToken(PasswordForm passwordForm) {
        logger.info("Updating Password");
        OperationStatus status = OperationStatus.FAILURE;
        int value = -1;
        passwordForm.setPassword(Hash.createHash(passwordForm.getPassword()));

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = new Member();
        logger.info(passwordForm + "PASSSWORD");
        ObjectMapper.mapObject(passwordForm, member, true);
        logger.info(member + "Only password");
        try {
            //double check
            Member existingMember = memberDAO.getMemberUsingToken(passwordForm.getToken());

            if (existingMember.getId() == member.getId()){
                if((value = memberDAO.updatePassword(member)) == 1){
                    value = memberDAO.invalidateToken(passwordForm.getToken());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't update password", e);
            status = OperationStatus.FAILURE;
        }
        if (value == 1){
            status = OperationStatus.SUCCESS;
        }

        logger.info(status.name() + "STATUS");
        return status;
    }

    @Override
    public OperationStatus updatePassword(Member member, PasswordForm passwordForm) {
        logger.info("Updating Password with current Password");
        OperationStatus status = OperationStatus.FAILURE;
        int value = -1;
        passwordForm.setPassword(Hash.createHash(passwordForm.getPassword()));

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member newmember = new Member();
        logger.info(passwordForm + "PASSSWORD");
        ObjectMapper.mapObject(passwordForm, newmember, true);
        logger.info(member + "Only password");
        try {
            //double check
            Member existingMember = memberDAO.getMember(member.getId());
            String currentPasswordHash = Hash.createHash(passwordForm.getCurrentPassword());
            logger.info(currentPasswordHash);
            if (existingMember.getPassword().equals(currentPasswordHash) ){
                value = memberDAO.updatePassword(newmember);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't update password", e);
            status = OperationStatus.FAILURE;
        }
        if (value == 1){
            status = OperationStatus.SUCCESS;
        }

        logger.info(status.name() + "STATUS");
        return status;
    }

    public int forgotPassword() {
        return 0;
    }

    public boolean isLoggedIn(long userId) {
        //return userId == CommonUtil.getLoggedInUserFromSession().getJobId();
        return false;
    }
}
