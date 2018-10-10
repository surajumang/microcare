package com.care.form;

import com.care.annotation.Flow;
import com.care.annotation.NotNull;
import com.care.annotation.Password;
import com.care.annotation.Size;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
@Flow(flowId = "getMemberType")
public class RegistrationForm extends EditProfileForm {

    private Logger logger = Logger.getLogger("RegistrationForm");

    private String password;
    private String password2;

    @NotNull
    @Password(message = "errors.password")
    @Size(min=5, max=100, message = "errors.password.size")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
        ActionErrors errors = super.validateCustom(request);

        if (! errors.isEmpty()){
            return errors;
        }
        if(! password.equals(password2)){
            errors.add("password2", new ActionMessage("errors.password.mismatch"));
        }

        return errors;
    }
}
