package com.care.service;

import com.care.beans.Member;
import com.care.dto.form.LoginDetails;

public interface AuthenticationService extends Service {
    Member login(LoginDetails loginDetails);


}
