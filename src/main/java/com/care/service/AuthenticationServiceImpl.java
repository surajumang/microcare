package com.care.service;

import com.care.dao.MemberDAOImpl;
import com.care.exception.DataReadException;
import com.care.exception.MemberNotFoundException;
import com.care.exception.TokenNotFoundException;
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

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member;
        try {
            member = memberDAO.getMember(loginForm.getEmail());
            logger.info(member + " ");
            logger.info("Member Exists " + member);
            if (Hash.createHash(loginForm.getPassword()).equals(member.getPassword())){
                status =OperationStatus.SUCCESS;
                logger.info("LoginAction success");
            }
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Can't fetch member for LoginAction", e);
            throw new MemberNotFoundException(e);
        }
        logger.info(status.name() + "STATUS");
        return status;
    }

    /*
    InvalidTokenException[todo]
     */
    public OperationStatus updatePasswordWithToken(PasswordResetForm passwordResetForm) {
        logger.info("Updating Password");
        OperationStatus status = OperationStatus.SUCCESS;
        int value = -1;
        passwordResetForm.setPassword(Hash.createHash(passwordResetForm.getPassword()));
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        try {
            //double check
            Member member = memberDAO.getMember(Long.valueOf(passwordResetForm.getId()));
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
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Can't update password", e);
            throw new TokenNotFoundException(e);
        }
        if (value != 1){
            status = OperationStatus.FAILURE;
        }
        logger.info(status.name() + "STATUS");
        return status;
    }

    @Override
    public OperationStatus updatePassword(Member member, PasswordUpdateForm passwordUpdateForm) {
        logger.info("Updating Password with current Password");
        OperationStatus status = OperationStatus.SUCCESS;
        int value = -1;
        passwordUpdateForm.setPassword(Hash.createHash(passwordUpdateForm.getPassword()));
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);

        try {
            Member existingMember = memberDAO.getMember(member.getId());
            String currentPasswordHash = Hash.createHash(passwordUpdateForm.getCurrentPassword());
            logger.info(currentPasswordHash);

            if (existingMember.getPassword().equals(currentPasswordHash) ){
                existingMember.setPassword(passwordUpdateForm.getPassword());
                value = memberDAO.updatePassword(existingMember);
            }
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Can't update password", e);
            throw new MemberNotFoundException(e);
        }
        if (value != 1){
            status = OperationStatus.FAILURE;
        }
        logger.info(status.name() + "STATUS");
        return status;
    }
}
