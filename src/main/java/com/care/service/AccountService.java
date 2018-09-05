package com.care.service;

import com.care.model.Member;
import com.care.dto.form.RegistrationFormDTO;

public interface AccountService extends Service {

    boolean enroll(RegistrationFormDTO registrationFormDTO);

    Member getMember(String email);

    int deleteMember(int memberId);

    int editMember(int memberId, RegistrationFormDTO registrationFormDTO);
}
