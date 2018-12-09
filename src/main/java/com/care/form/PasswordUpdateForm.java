package com.care.form;

import com.care.annotation.NotEmpty;
import com.care.controller.ControllerUtil;
import com.care.model.Member;
import com.care.service.Hash;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class PasswordUpdateForm extends PasswordResetForm {
    private String currentPassword;

    @NotEmpty
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        ActionErrors errors =  super.validateCustom(request);
        if (! errors.isEmpty()){
            return errors;
        }
        if (! Hash.createHash(currentPassword).equals(member.getPassword())){
            errors.add("currentPassword", new ActionMessage("errors.password.incorrect"));
        }
        return errors;
    }
}
