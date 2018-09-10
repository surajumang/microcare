package com.care.service;

import com.care.dao.*;
import com.care.dto.form.EditForm;
import com.care.exception.MemberAlreadyRegisteredException;
import com.care.model.*;
import com.care.dto.form.RegistrationFormDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AccountServiceImpl implements AccountService {

    private static Logger logger = Logger.getLogger("AccountService");
    public AccountServiceImpl() {  }

    public OperationStatus enroll(RegistrationFormDTO registrationFormDTO) throws MemberAlreadyRegisteredException {

        OperationStatus status = OperationStatus.FAILURE;
        int val = -1;
        logger.info("Called enroll  " + registrationFormDTO);

        if (registrationFormDTO.getMemberType().equals("SEEKER") ){
            Seeker seeker = new Seeker();
            registrationFormDTO.setPassword(Hash.createHash(registrationFormDTO.getPassword()));

            logger.info("Seeker details--" + seeker);
            ObjectMapper.mapObject(registrationFormDTO, seeker, true);
            val = addSeeker(seeker);
        }
        else {
            Sitter sitter = new Sitter();
            registrationFormDTO.setPassword(Hash.createHash(registrationFormDTO.getPassword()));
            logger.info("WIth UPdated Password " + registrationFormDTO.getPassword());
            logger.info("Seeker details--" + sitter);
            ObjectMapper.mapObject(registrationFormDTO, sitter, true);
            val = addSitter(sitter);
        }
        if (val == 1){
            status = OperationStatus.SUCCESS;
        }
        return status;
    }

    private int addSeeker(Seeker seeker) throws MemberAlreadyRegisteredException{
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        logger.info(seeker.toString());
        int status = -1;
        try {
            status = seekerDAO.addSeeker(seeker);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't add", e);
            // throw exception.
            throw new MemberAlreadyRegisteredException("Already Registered");
        }
        return status;
    }

    private int addSitter(Sitter sitter) throws MemberAlreadyRegisteredException{

        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        logger.info(sitter.toString());
        int status = -1;

        try {
            status = sitterDAO.addSitter(sitter);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't add", e);
            throw new MemberAlreadyRegisteredException("Already registered");
        }
        return status;
    }

    public Member getMember(String email) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member;
        try {
            member = memberDAO.getMember(email);
            logger.info(member.toString());
        } catch (java.sql.SQLException e) {
           logger.log(Level.SEVERE, "Error fetching member", e);
           member = Member.EMPTY_MEMBER;
        }
        return member;
    }

    @Override
    public Member getMemberUsingToken(String token) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member;
        try {
            member = memberDAO.getMemberUsingToken(token);
            logger.info(member + "found using Token" + token);
        } catch (java.sql.SQLException e) {
            logger.log(Level.SEVERE, "Error fetching member using token. Doesn't exist", e);
            member = Member.EMPTY_MEMBER;
        }
        return member;
    }

    @Override
    public OperationStatus setMemberStatus(Member member, Status status) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        OperationStatus operationStatus = OperationStatus.FAILURE;
        try {
            if (memberDAO.setMemberStatus(member.getId(), Status.ACTIVE) == 1){
                operationStatus = OperationStatus.SUCCESS;
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "CAN't set member status", e);
            operationStatus = OperationStatus.FAILURE;
        }
        return operationStatus;
    }

    public OperationStatus mailPasswordResetToken(String email, String contextPath) {

        OperationStatus operationStatus = OperationStatus.FAILURE;
        Member member = getMember(email);

        if (member != Member.EMPTY_MEMBER){
            logger.info("Member okay" + member);
            PasswordResetToken token = generatePasswordResetToken(member);
            //set token to DB.
            MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
           try {
               memberDAO.addToken(token);
               // code to send email.
               SendMail.sendMail(email, contextPath + "/visitor/VerifyToken.do?token=" + token.getToken());
               logger.info("mail sent");
               operationStatus = OperationStatus.SUCCESS;
           }catch (SQLException e){
               operationStatus = OperationStatus.FAILURE;
           }

        }
        return operationStatus;
    }

    /*
    Write code related to authorization here.
    Deleting member requires all jobs and application deletion as well based on the member type..

     */
    public OperationStatus deleteMember(Member member) {

        OperationStatus status = OperationStatus.SUCCESS;
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);

        try {
            if (member.getMemberType() == MemberType.SEEKER){
                logger.info("A SEEKer was deleted");
                applicationDAO.setAllApplicationsOnJobsPostedBy(member.getId(), Status.EXPIRED);
                jobDAO.setAllJobsStatus(member.getId(), Status.EXPIRED);
            }else {
                logger.info("A sitter was deleted");
                applicationDAO.setAllApplicationsStatusBySitter(member.getId(),Status.EXPIRED );
            }
            memberDAO.setMemberStatus(member.getId(), Status.CLOSED);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error Deleting member", e);
            status = OperationStatus.FAILURE;
        }
        return status;
    }

    private PasswordResetToken generatePasswordResetToken(Member member) {

        String token = "";
        for (int i = 0; i<10; i++){
            token += String.valueOf((int)(Math.random()*10));
        }
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setId(member.getId());
        passwordResetToken.setExpirationDate(new Date(System.currentTimeMillis() + 24*3600*1000));
        passwordResetToken.setStatus(Status.ACTIVE);
        passwordResetToken.setToken(token);

        return passwordResetToken;
    }

    private OperationStatus setToken(PasswordResetToken passwordResetToken){
        OperationStatus operationStatus=OperationStatus.SUCCESS;
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        try{
            memberDAO.addToken(passwordResetToken);
        }catch(SQLException e){
            operationStatus = OperationStatus.FAILURE;
            logger.log(Level.SEVERE, "Can't set token ", e);
        }
        return operationStatus;
    }

    public int editMember(long memberId, EditForm editForm) {
        int status = -1;
        logger.info("Called enroll");

        if (editForm.getMemberType().equals("SEEKER") ){
            Seeker seeker = new Seeker();
            ObjectMapper.mapObject(editForm, seeker, true);
            status = editSeeker( memberId, seeker);
        }
        else {
            Sitter sitter = new Sitter();
            ObjectMapper.mapObject(editForm, sitter, true);
            status = editSitter(memberId, sitter);
        }
        return status ;
    }

    private int editSeeker(long seekerId, Seeker seeker) {

        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        logger.info(seeker.toString());
        int status = -1;
        try {
            status = seekerDAO.editSeeker(seekerId, seeker);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't edit ", e);
        }
        return status;
    }

    private int editSitter(long sitterId, Sitter sitter){
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        logger.info(sitter.toString());
        int status = -1;
        try {
            status = sitterDAO.editSitter(sitterId, sitter);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't edit", e);
        }
        return status;
    }

    private static final class SendMail {

        public static void sendMail(String email, String token) {

            final String username = "sjkumar@apostek.com";
            final String password = "me@company512";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("sjkumar@apostek.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("Password reset token");
                message.setText(token);

                Transport.send(message);

                logger.info("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
