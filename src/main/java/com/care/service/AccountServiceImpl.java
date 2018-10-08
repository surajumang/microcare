package com.care.service;

import com.care.dao.*;
import com.care.exception.DataReadException;
import com.care.exception.MemberNotFoundException;
import com.care.exception.TokenNotFoundException;
import com.care.form.EditProfileForm;
import com.care.exception.MemberAlreadyRegisteredException;
import com.care.form.RegistrationForm;
import com.care.model.*;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AccountServiceImpl implements AccountService {

    private static Logger logger = Logger.getLogger("AccountService");
    public AccountServiceImpl() {  }

    public OperationStatus enroll(RegistrationForm registrationForm) throws MemberAlreadyRegisteredException {
        OperationStatus status = OperationStatus.FAILURE;
        int val = -1;
        logger.info("Called enroll  " + registrationForm);

        if (registrationForm.getMemberType().equals("SEEKER") ){
            Seeker seeker = new Seeker();
            registrationForm.setPassword(Hash.createHash(registrationForm.getPassword()));

            logger.info("Seeker details--" + seeker);
            ObjectMapper.mapObject(registrationForm, seeker, true);
            val = addSeeker(seeker);
        }
        else {
            Sitter sitter = new Sitter();
            registrationForm.setPassword(Hash.createHash(registrationForm.getPassword()));
            logger.info("WIth UPdated Password " + registrationForm.getPassword());
            logger.info("Seeker details--" + sitter);
            ObjectMapper.mapObject(registrationForm, sitter, true);
            val = addSitter(sitter);
        }
        if (val == 1){
            status = OperationStatus.SUCCESS;
        }
        return status;
    }
    /*
    This case has been handled in Validation. Exception not required.
     */
    private int addSeeker(Seeker seeker) throws MemberAlreadyRegisteredException{
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        logger.info(seeker.toString());
        int status = -1;
        try {
            status = seekerDAO.addSeeker(seeker);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't add", e);
            // [todo]this is never being thrown
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can't add", e);
            throw new MemberAlreadyRegisteredException("Already registered");
        }
        return status;
    }
    /*
    MemberNotFoundException[todo]
     */
    public Member getMember(String email) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member;
        try {
            member = memberDAO.getMember(email);
            logger.info(member.toString());
        } catch (DataReadException e) {
           logger.log(Level.SEVERE, "Error fetching member", e);
           throw new MemberNotFoundException(e);
        }
        return member;
    }

    public Member getMember(long id) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Member member;
        try {
            member = memberDAO.getMember(id);
            logger.info(member.toString());
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Error fetching member", e);
            throw new MemberNotFoundException(e);
        }
        return member;
    }

    /*
    TokenNotFoundException[todo]
     */
    @Override
    public Token getToken(String token) {
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        Token token1;
        try {
            token1 = memberDAO.getToken(token);
            logger.info(token1 + "found using Token" + token);
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Error fetching token. Doesn't exist", e);
            throw new TokenNotFoundException(e);
        }
        return token1;
    }

    /*
    EMailNotSentException[todo]
     */
    public OperationStatus mailPasswordResetToken(String email, String contextPath) {
        OperationStatus operationStatus = OperationStatus.FAILURE;
        //getting the member stored in database.
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        try {
            Member member = getMember(email);
            logger.info("Member okay" + member);
            Token token = generatePasswordResetToken(member);
            //set token to DB.
            if (memberDAO.addToken(token) == 1){
                String message = "localhost:8080/"+contextPath + "/visitor/captureToken.do?token=" + token.getToken();
                sendMail(email, message);
                logger.info("mail sent");
                operationStatus = OperationStatus.SUCCESS;
            }
        }catch (DataReadException e){
            throw new MemberNotFoundException(e);
        }
        return operationStatus;
    }

    public OperationStatus deleteMember(Member member) {
        OperationStatus status = OperationStatus.SUCCESS;
        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);

        try {
            if (member.isSeeker()){
                logger.info("A SEEKer was deleted");
                Seeker seeker = seekerDAO.getSeeker(member.getId());
                seeker.closeAccount();
            }else {
                logger.info("A sitter was deleted");
                Sitter sitter = sitterDAO.getSitter(member.getId());
                sitter.closeAccount();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error Deleting member", e);
            status = OperationStatus.FAILURE;
        }
        return status;
    }

    private Token generatePasswordResetToken(Member member) {
        String token = "";
        for (int i = 0; i<10; i++){
            token += String.valueOf((int)(Math.random()*10));
        }
        Token passwordResetToken = new Token();
        passwordResetToken.setMember(member);
        passwordResetToken.setExpirationDate(new Timestamp(System.currentTimeMillis() + 24*3600*1000));
        passwordResetToken.setStatus(Status.ACTIVE);
        passwordResetToken.setToken(token);

        return passwordResetToken;
    }

    private OperationStatus setToken(Token token){
        OperationStatus operationStatus=OperationStatus.SUCCESS;
        MemberDAO memberDAO = DAOFactory.get(MemberDAOImpl.class);
        try{
            memberDAO.addToken(token);
        }catch(Exception e){
            operationStatus = OperationStatus.FAILURE;
            logger.log(Level.SEVERE, "Can't set token ", e);
        }
        return operationStatus;
    }

    public int editMember(long memberId, EditProfileForm editProfileForm) {
        int status = -1;
        logger.info("Called enroll");

        if (editProfileForm.getMemberType().equals("SEEKER") ){
            status = editSeeker( memberId, editProfileForm);
        }
        else {
            status = editSitter(memberId, editProfileForm);
        }
        return status ;
    }

    private int editSeeker(long seekerId, EditProfileForm newSeeker) {

        SeekerDAO seekerDAO = DAOFactory.get(SeekerDAOImpl.class);
        logger.info(newSeeker.toString());
        int status = 1;
        try {
            Seeker seeker = seekerDAO.getSeeker(seekerId);
            ObjectMapper.mapObject(newSeeker, seeker, true);
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Can't edit ", e);
            throw new MemberNotFoundException(e);
        }
        return status;
    }

    private int editSitter(long sitterId, EditProfileForm newSitter){
        SitterDAO sitterDAO = DAOFactory.get(SitterDAOImpl.class);
        logger.info(newSitter.toString());
        int status = 1;
        try {
            Sitter sitter = sitterDAO.getSitter(sitterId);
            ObjectMapper.mapObject(newSitter, sitter, true);
            //status = sitterDAO.editSitter(sitterId, sitter);
        } catch (DataReadException e) {
            logger.log(Level.SEVERE, "Can't edit", e);
            throw new MemberNotFoundException(e);
        }
        return status;
    }

    /*
    [todo] Throw EmailNotSentException
     */
    public void sendMail(String email, String token) {

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
                String anchorMessage = "<a href=\"" + token + "\">" + token + "</a>";
                logger.info(anchorMessage);
                message.setContent(anchorMessage, "text/html; charset=utf-8");

                Transport.send(message);

                logger.info("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
    }

}
