package com.care.service;

import com.care.model.Member;
import com.care.dto.form.RegistrationFormDTO;

public interface AccountService extends Service {

    boolean registerMember(RegistrationFormDTO registrationFormDTO);
    Member getMember(String email);
    int deleteMember(String email);
    int editMember(String email, RegistrationFormDTO registrationFormDTO);
}
