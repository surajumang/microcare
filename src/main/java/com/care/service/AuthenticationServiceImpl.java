package com.care.service;

import com.care.dao.HMemberDAOImpl;
import com.care.form.LoginForm;
import com.care.form.PasswordResetForm;
import com.care.form.PasswordUpdateForm;
import com.care.model.Member;
import com.care.dao.DAOFactory;
import com.care.dao.MemberDAO;
import com.care.model.Status;
import com.care.model.Token;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger logger = Logger.getLogger("AuthenticationServiceImpl");
    public AuthenticationServiceImpl(){

    }

    public OperationStatus loginUser(LoginForm loginForm) {
        logger.info("User trying to Log in");
        OperationStatus status = OperationStatus.FAILURE;

        MemberDAO memberDAO = DAOFactory.get(HMemberDAOImpl.class);
        Member member = null;
        try {
            member = memberDAO.getMember(loginForm.getEmail());
            logger.info(member + " ");
        } catch (Exception e) {
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
    /*
    InvalidTokenException[todo]
     */
    public OperationStatus updatePasswordWithToken(PasswordResetForm passwordResetForm) {
        logger.info("Updating Password");
        OperationStatus status = OperationStatus.FAILURE;
        int value = -1;
        passwordResetForm.setPassword(Hash.createHash(passwordResetForm.getPassword()));

        MemberDAO memberDAO = DAOFactory.get(HMemberDAOImpl.class);

        try {
            //double check
            Member member = memberDAO.getMember(Long.valueOf(passwordResetForm.getId()));
            if (member == Member.emptyMember()){
                //[todo] throw some
            }
            logger.info(passwordResetForm + "PASSSWORD");
            ObjectMapper.mapObject(passwordResetForm, member, true);
            Token existingToken = memberDAO.getToken(passwordResetForm.getToken());

            if (existingToken != Token.emptyToken() && existingToken.getMember().getId() == member.getId()){
                if (existingToken.getStatus() == Status.ACTIVE &&
                        existingToken.getExpirationDate().after(new Timestamp(System.currentTimeMillis()))){

                    if(memberDAO.updatePassword(member) == 1){
                        value = memberDAO.invalidateToken(passwordResetForm.getToken());
                    }
                }
            }
        } catch (Exception e) {
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
    public OperationStatus updatePassword(Member member, PasswordUpdateForm passwordUpdateForm) {
        logger.info("Updating Password with current Password");
        OperationStatus status = OperationStatus.FAILURE;
        int value = -1;
        passwordUpdateForm.setPassword(Hash.createHash(passwordUpdateForm.getPassword()));
        MemberDAO memberDAO = DAOFactory.get(HMemberDAOImpl.class);

        try {
            Member existingMember = memberDAO.getMember(member.getId());
            String currentPasswordHash = Hash.createHash(passwordUpdateForm.getCurrentPassword());
            logger.info(currentPasswordHash);

            if (existingMember.getPassword().equals(currentPasswordHash) ){
                existingMember.setPassword(passwordUpdateForm.getPassword());
                value = memberDAO.updatePassword(existingMember);
            }
        } catch (Exception e) {
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
