package com.care.service;

import com.care.beans.Member;
import com.care.dto.form.RegistrationForm;

public interface MemberService extends Service {
    boolean registerMember(RegistrationForm registrationForm);
    int getMember(int memberId);
    int deleteMember(int memberId);
    int editMember(int memberId, RegistrationForm registrationForm);
}
