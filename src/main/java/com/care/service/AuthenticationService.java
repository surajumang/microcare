package com.care.service;

import com.care.dto.form.LoginDetails;

public interface AuthenticationService extends Service {

    boolean loginUser(LoginDetails loginDetails);

    boolean logout();

    int updatePassword();

    int forgotPassword();

    boolean isLoggedIn(int userId);

}
