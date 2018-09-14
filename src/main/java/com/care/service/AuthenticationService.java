package com.care.service;

import com.care.form.LoginForm;
import com.care.form.PasswordForm;
import com.care.model.Member;

public interface AuthenticationService extends Service {

    OperationStatus loginUser(LoginForm loginForm);

    boolean logout();

    OperationStatus updatePasswordWithToken(PasswordForm passwordForm);

    OperationStatus updatePassword(Member member, PasswordForm passwordForm);

    int forgotPassword();

    boolean isLoggedIn(long userId);

}
