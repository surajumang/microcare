package com.care.form;

import com.care.annotation.Email;
import com.care.annotation.Name;
import com.care.annotation.Number;
import com.care.dao.DAOFactory;
import com.care.dao.HMemberDAOImpl;
import com.care.dao.MemberDAO;
import com.care.model.Member;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationForm extends EditProfileForm {

    private Logger logger = Logger.getLogger("RegistrationForm");

    private String password;
    private String password2;

    @Name(regex = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{5,}", message = "errors.password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public ActionErrors validateCustom() {
        ActionErrors errors = new ActionErrors();
        errors = super.validateCustom();

        MemberDAO memberDAO =DAOFactory.get(HMemberDAOImpl.class);
        if(! password.equals(password2)){
            errors.add("password2", new ActionMessage("errors.password.mismatch"));
        }
        if(getEmail().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
            errors.add("email", new ActionMessage("errors.email"));
        }
        else {
            try {
                Member member = memberDAO.getMember(getEmail());
                if (member != null){
                    errors.add("email", new ActionMessage("errors.email.exist"));
                }
            } catch (Exception e) {
                // more than one row exist for the email. {should never happen}
            }
        }

        return errors;
    }
}
