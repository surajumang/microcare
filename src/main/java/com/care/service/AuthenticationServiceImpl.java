package com.care.service;

import com.care.dto.form.PasswordDTO;
import com.care.model.Member;
import com.care.dao.DAOFactory;
import com.care.dao.MemberDAO;
import com.care.dao.MemberDAOImpl;
import com.care.dto.form.LoginDetails;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger logger = Logger.getLogger("AuthenticationServiceImpl");
    public AuthenticationServiceImpl(){

    }

    public OperationStatus loginUser(LoginDetails loginDetails) {
        logger.info("User trying to Log in");
        OperationStatus status = OperationStatus.FAILURE;

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = null;
        try {
            member = memberDAO.getMember(loginDetails.getEmail());
            logger.info(member + " ");
        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "Can't fetch member for Login", e);
            status = OperationStatus.FAILURE;
        }

        if(member != null){
            logger.info("Member Exists " + member);

            if (Hash.createHash(loginDetails.getPassword()).equals(member.getPassword())){
                status =OperationStatus.SUCCESS;
                logger.info("Login success");
            }
        }
        logger.info(status.name() + "STATUS");
        return status;
    }

    public boolean logout() {
        //CommonUtil.removeLoggedInUser();
        return false;
    }

    public OperationStatus updatePassword(PasswordDTO passwordDTO) {
        logger.info("Updating Password");
        OperationStatus status = OperationStatus.FAILURE;
        int value = -1;
        passwordDTO.setPassword(Hash.createHash(passwordDTO.getPassword()));

        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member = new Member();
        logger.info(passwordDTO + "PASSSWORD");
        ObjectMapper.mapObject(passwordDTO, member, true);
        logger.info(member + "Only password");
        try {
            //double check
            Member existingMember = memberDAO.getMemberUsingToken(passwordDTO.getToken());

            if (existingMember.getId() == member.getId()){
                if((value = memberDAO.updatePassword(member)) == 1){
                    value = memberDAO.invalidateToken(passwordDTO.getToken());
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

    public int forgotPassword() {
        return 0;
    }

    public boolean isLoggedIn(long userId) {
        //return userId == CommonUtil.getLoggedInUserFromSession().getJobId();
        return false;
    }
}
