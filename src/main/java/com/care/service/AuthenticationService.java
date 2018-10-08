package com.care.service;

import com.care.form.LoginForm;
import com.care.form.PasswordResetForm;
import com.care.form.PasswordUpdateForm;
import com.care.model.Member;

public interface AuthenticationService extends Service {

    OperationStatus loginUser(LoginForm loginForm);

    OperationStatus updatePasswordWithToken(PasswordResetForm passwordResetForm);

    OperationStatus updatePassword(Member member, PasswordUpdateForm passwordUpdateForm);
}
