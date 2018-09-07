package com.care.service;

import com.care.dto.form.EditForm;
import com.care.model.Member;
import com.care.dto.form.RegistrationFormDTO;

public interface AccountService extends Service {

    OperationStatus enroll(RegistrationFormDTO registrationFormDTO);

    Member getMember(String email);

    OperationStatus deleteMember(Member member);

    int editMember(int memberId, EditForm editForm);
}
