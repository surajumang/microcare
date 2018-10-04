package com.care.service;

import com.care.form.EditProfileForm;
import com.care.exception.MemberAlreadyRegisteredException;
import com.care.form.RegistrationForm;
import com.care.model.Member;
import com.care.model.Token;
import com.care.model.Status;

public interface AccountService extends Service {

    OperationStatus enroll(RegistrationForm registrationForm) throws MemberAlreadyRegisteredException;

    Member getMember(String email);

    Member getMember(long id);

    Token getToken(String token);

    OperationStatus setMemberStatus(Member member, Status status);

    OperationStatus mailPasswordResetToken(String email, String contextPath);

    OperationStatus deleteMember(Member member);

    int editMember(long memberId, EditProfileForm editProfileForm);
}
