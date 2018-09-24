package com.care.form;

import com.care.annotation.NotNull;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

public class PasswordUpdateForm extends PasswordResetForm {
    String currentPassword;

    @NotNull
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Override
    public ActionErrors validateCustom() {
        return super.validateCustom();
        // write code to check if the password is correct or not.
    }
}
