package com.care.service;

import com.care.beans.Member;
import com.care.dto.form.RegistrationForm;

public interface MemberService extends Service {
    boolean registerMember(RegistrationForm registrationForm);
    int deleteMember(int memberId);
    int editMember(int memberId, Member member);
}
