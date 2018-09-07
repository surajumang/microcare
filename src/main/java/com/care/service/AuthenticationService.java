package com.care.service;

import com.care.dto.form.LoginDetails;

public interface AuthenticationService extends Service {

    OperationStatus loginUser(LoginDetails loginDetails);

    boolean logout();

    int updatePassword();

    int forgotPassword();

    boolean isLoggedIn(int userId);

}
