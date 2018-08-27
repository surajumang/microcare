package com.care.service;

import com.care.dto.form.RegistrationForm;

public interface MemberService extends Service {
    public boolean registerMember(RegistrationForm registrationForm);
}
