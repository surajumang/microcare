package com.care.service;

import com.care.dto.form.RegistrationFormDTO;

public interface MemberService extends Service {
    boolean registerMember(RegistrationFormDTO registrationFormDTO);
    int getMember(int memberId);
    int deleteMember(int memberId);
    int editMember(int memberId, RegistrationFormDTO registrationFormDTO);
}
