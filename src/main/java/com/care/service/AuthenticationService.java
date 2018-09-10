package com.care.service;

import com.care.dto.form.LoginDetails;
import com.care.dto.form.PasswordDTO;
import com.care.model.Member;

public interface AuthenticationService extends Service {

    OperationStatus loginUser(LoginDetails loginDetails);

    boolean logout();

    OperationStatus updatePasswordWithToken(PasswordDTO passwordDTO);

    OperationStatus updatePassword(Member member, PasswordDTO passwordDTO);

    int forgotPassword();

    boolean isLoggedIn(long userId);

}
