package com.care.form;

import com.care.annotation.Name;
import com.care.annotation.NotNull;
import com.care.dao.DAOFactory;
import com.care.dao.HMemberDAOImpl;
import com.care.dao.MemberDAO;
import com.care.model.Member;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.util.logging.Logger;

public class RegistrationForm extends EditProfileForm {

    private Logger logger = Logger.getLogger("RegistrationForm");

    private String password;
    private String password2;

    @NotNull
    @Name(regex = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{5,}", message = "errors.password")
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
    public ActionErrors validateCustom() {
        ActionErrors errors = super.validateCustom();

        if (! errors.isEmpty()){
            return errors;
        }
        if(! password.equals(password2)){
            errors.add("password2", new ActionMessage("errors.password.mismatch"));
        }

        return errors;
    }
}
