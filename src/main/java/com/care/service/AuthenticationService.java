package com.care.service;

import com.care.dto.form.LoginDetails;
import com.care.dto.form.PasswordDTO;

public interface AuthenticationService extends Service {

    OperationStatus loginUser(LoginDetails loginDetails);

    boolean logout();

    OperationStatus updatePassword(PasswordDTO passwordDTO);

    int forgotPassword();

    boolean isLoggedIn(long userId);

}
