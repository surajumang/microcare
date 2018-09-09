package com.care.service;

import com.care.dto.form.EditForm;
import com.care.exception.MemberAlreadyRegisteredException;
import com.care.model.Member;
import com.care.dto.form.RegistrationFormDTO;

public interface AccountService extends Service {

    OperationStatus enroll(RegistrationFormDTO registrationFormDTO) throws MemberAlreadyRegisteredException;

    Member getMember(String email);

    Member getMemberUsingToken(String token);

    OperationStatus mailPasswordResetToken(String email, String contextPath);

    OperationStatus deleteMember(Member member);

    int editMember(long memberId, EditForm editForm);
}
